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
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.decima.xml.templating.document.post.template;

import gov.nist.decima.core.document.DocumentException;
import gov.nist.decima.core.document.handling.ResourceResolver;
import gov.nist.decima.xml.document.MutableXMLDocument;

import java.net.URL;
import java.util.List;

/**
 * Represents a template processor that applies a number of {@code Action} transforms to a template
 * document.
 */
public interface TemplateProcessor {
    /*
     * Retrieve the URL of the template definition file.
     * 
     * @return a URL pointing to the template definition file
     */
    URL getContextSystemId();

    /**
     * Retrieve the URL for the base template that is used for the transformations.
     * 
     * @return a URL pointing to the template to use
     */
    URL getBaseTemplateURL();

    /**
     * Retrieves the sequence of actions to be applied to the template.
     * 
     * @return a list of actions
     */
    List<Action> getActions();

    /**
     * Creates a resulting {@code Template} by processing a set of {@code Action} transforms against
     * a base template.
     * 
     * @param resolver
     *            the TemplateResolver to use to load the template
     * @return a new {@code Document} based on the provided template and transformations
     * @throws DocumentException
     *             if a processing error occurs
     */
    MutableXMLDocument generate(ResourceResolver<MutableXMLDocument> resolver) throws DocumentException;
}