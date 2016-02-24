/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

/*
 * This source code implements specifications defined by the Java
 * Community Process. In order to remain compliant with the specification
 * DO NOT add / change / or delete method signatures!
 */

package javax.portlet.annotations;


import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * <div class='changed_added_3_0'>
 * Designates a method corresponding to the renderHeaders method of 
 * the <code>HeaderPortlet</code> interface.
 * The annotated method must have the following signature:
 * <p>
 *    <code>public void &lt;methodName&gt;(HeaderRequest, HeaderResponse)</code>
 * <p>   
 * where the method name can be freely selected.
 * <p>
 * The method declaration may contain a throws clause. Exceptions declared in the 
 * throws clause should be of type {@link javax.portlet.PortletException} or
 * {@link java.io.IOException}. 
 * Checked exceptions of any other type will be caught, wrapped with a PortletException, 
 * and rethrown.
 * <p>
 * The header method can be used to set properties such as cookies, header values, and 
 * XML elements for the <code>HEAD</code> section of the aggregated portal document.
 * <p>
 * Valid <code>HEAD</code> section markup written to the <code>HeaderResponse</code>
 * output stream or writer will be added to the aggregated portal document <code>HEAD</code>
 * section subject to portlet container-imposed restrictions. 
 * </div>
 *    
 * @see javax.portlet.PortletResponse the add and set properties methods of PortletResponse
 * @see javax.portlet.MimeResponse#MARKUP_HEAD_ELEMENT MimeResponse#MARKUP_HEAD_ELEMENT
 * @see javax.portlet.HeaderPortlet
 */

@Retention(RUNTIME) @Target({METHOD})
public @interface HeaderMethod {
   
   /**
    * The portlet names for which the header method applies.
    * <p>
    * The annotated method can apply to multiple portlets within the portlet
    * application. The names of the portlets to which the resources apply must be 
    * specified in this field.
    * <p>
    * A wildcard character '*' can be specified in the first portletName array element 
    * to indicate that the resource declarations are to apply to all portlets in 
    * the portlet application.
    * If specified, the wildcard character must appear alone in the first array element.
    * 
    * @return     The portlet names
    */
   String[]   portletNames();
   
   /**
    * The portlet mode rendered by the annotated method.
    * <p>
    * If an portlet mode is specified, the bean enabler will dispatch Header requests with 
    * matching portlet mode values to this method. 
    * <p>
    * If this field is empty, the method will be executed for all
    * Header requests not dispatched by portlet mode to other HeaderMethods.
    * 
    * @return     The portlet mode
    */
   String   portletMode() default "";
   
   /**
    * Sets the content type, or the MIME type, of content generated by the method.
    * The content type will be set before the annotated method body is executed.
    * <p>
    * If this field is empty, no content type will be set.
    * The portlet can then set the content type using the portlet API
    * <code>HeaderResponse#setContentType</code> method.
    * 
    * @see        javax.portlet.HeaderResponse#setContentType(String) HeaderResponse#setContentType
    * 
    * @return     The content type
    */
   String   contentType() default "text/html";
   
   /**
    * Specifies a resource, such as a JSP, an HTML file, or a servlet to be included.
    * <p>
    * The resource will be included using the 
    * <code>PortletRequestDispatcher#include</code> method after the method body 
    * has been executed.
    * <p>
    * If this field is empty, no resource will be included.
    * 
    * @see        javax.portlet.PortletRequestDispatcher
    * @see        javax.portlet.PortletRequestDispatcher#include(javax.portlet.PortletRequest, javax.portlet.PortletResponse) PortletRequestDispatcher#include
    * 
    * @return     The resource to be included
    */
   String   include() default "";
   
   /**
    * The ordinal number for this annotated method.
    * <p>
    * The ordinal number determines the order of execution if multiple methods
    * are annotated.
    * Annotated methods with a lower ordinal number are executed before methods with
    * a higher ordinal number.
    * 
    * @return     The ordinal number
    */
   int        ordinal() default 0;
}
