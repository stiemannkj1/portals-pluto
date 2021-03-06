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

package basic.portlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static basic.portlet.Constants.PARAM_IMGNAME;
import static basic.portlet.Constants.PARAM_SELTYPE;
import static basic.portlet.Constants.PARAM_SELTYPE_RADIO;
import static basic.portlet.Constants.imgMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;


/**
 * A demo portlet for image selection.
 */
public class ImageSelPortlet extends GenericPortlet {

   // Set up logging
   private final Logger logger = LoggerFactory.getLogger(ImageSelPortlet.class);

   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {
      
      if (logger.isDebugEnabled()) {
         logger.debug(this.getClass().getName(), "doView", "Entry");
      }
      
      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
            "/WEB-INF/jsp/view-isp.jsp");
      rd.include(req, resp);

   }
   
   /* (non-Javadoc)
    * @see javax.portlet.GenericPortlet#serveResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
    */
   @SuppressWarnings("deprecation")
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp)
         throws PortletException, IOException {

	      resp.setContentType("text/html");
	      PrintWriter writer = resp.getWriter();

	      String pid = resp.getNamespace();
	      Set<String> names = imgMap.keySet();
	      String selType = req.getParameter(PARAM_SELTYPE);
	      selType = (selType == null) ? PARAM_SELTYPE_RADIO : selType;
	      String imgName = req.getParameter(PARAM_IMGNAME);
	      imgName = (imgName == null) ? "default" : imgName;

	      if (selType.equals(PARAM_SELTYPE_RADIO)) {
	         for (String name : names) {
	            writer.write("   <input type='radio' name='" + PARAM_IMGNAME + "' value='" + 
	               name + "'" + (name.equals(imgName) ? "checked" : "") + ">" + name);
	         }
	      } else {
	         writer.write("<select id='" + pid + "-selBox' name='" + PARAM_IMGNAME + "' size='1'>");
	         writer.write("   <option value='default' " + 
	               ("default".equals(imgName) ? "selected" : "") + ">-</option>");
	         for (String name : names) {
	            writer.write("   <option value='" + name + "'" + 
	               (name.equals(imgName) ? "selected" : "") + ">" + name + "</option>");
	         }
	         writer.write("</select>");
	      }
	}

   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
   }

}
