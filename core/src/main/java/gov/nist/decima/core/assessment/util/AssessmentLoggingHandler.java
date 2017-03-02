/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NASA BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.decima.core.assessment.util;

import gov.nist.decima.core.assessment.Assessment;
import gov.nist.decima.core.assessment.result.TestResult;
import gov.nist.decima.core.assessment.result.TestState;
import gov.nist.decima.core.document.Document;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssessmentLoggingHandler extends AbstractDelegatingLoggingHandler {
  private static final Logger log = LogManager.getLogger(AssessmentLoggingHandler.class);
  private static final AssessmentLoggingHandler INSTANCE = new AssessmentLoggingHandler(Level.INFO);

  /**
   * Retrieve the singleton notifier instance.
   * 
   * @return the singleton instance
   */
  public static AssessmentLoggingHandler instance() {
    return INSTANCE;
  }

  private final Level logLevel;

  public AssessmentLoggingHandler() {
    this(Level.INFO, null);
  }

  public AssessmentLoggingHandler(Level level) {
    this(level, null);
  }

  public AssessmentLoggingHandler(Level level, LoggingHandler delegate) {
    super(delegate);
    this.logLevel = level;
  }

  /**
   * Retrieve the log level to use when logging.
   * 
   * @return the logLevel
   */
  public Level getLogLevel() {
    return logLevel;
  }

  @Override
  public <DOC extends Document> void addTestResult(Assessment<? extends DOC> assessment, DOC document,
      String derivedRequirementId, TestResult result) {
    // TODO Auto-generated method stub
    super.addTestResult(assessment, document, derivedRequirementId, result);
  }

  @Override
  public <DOC extends Document> void assignTestStatus(Assessment<? extends DOC> assessment, DOC document,
      String derivedRequirementId, TestState state) {
    // TODO Auto-generated method stub
    super.assignTestStatus(assessment, document, derivedRequirementId, state);
  }

  @Override
  public <DOC extends Document> void assessmentExecutionStarted(DOC document) {
    super.assessmentExecutionStarted(document);

    log.log(getLogLevel(), "Starting assessment execution");
  }

  @Override
  public <DOC extends Document> void assessmentExecutionCompleted(DOC document) {
    super.assessmentExecutionCompleted(document);

    log.log(getLogLevel(), "Assessment execution completed");
  }

  @Override
  public <DOC extends Document> void assessmentStarted(Assessment<? extends DOC> assessment, DOC document) {
    super.assessmentStarted(assessment, document);

    Level level = getLogLevel();
    if (log.isEnabled(level)) {
      log.log(level, "Executing assessment: " + assessment.getName(true));
    }
  }

  @Override
  public <DOC extends Document> void assessmentCompleted(Assessment<? extends DOC> assessment, DOC document) {
    super.assessmentCompleted(assessment, document);

    Level level = getLogLevel();
    if (log.isEnabled(level)) {
      log.log(level, "Assessment completed: {}", assessment.getName(false));
    }
  }

  @Override
  public <DOC extends Document> void assessmentError(Assessment<? extends DOC> assessment, DOC document, Throwable th) {
    super.assessmentError(assessment, document, th);

    Level level = getLogLevel();
    if (log.isEnabled(level)) {
      log.log(level, "Error performing assessment: " + assessment.getName(false), th);
    }
  }
}