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


package org.apache.pluto.container.bean.processor.fixtures.mocks;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.xml.namespace.QName;


/**
 * @author Scott Nicklous
 *
 */
public class MockEventRequest extends MockClientDataRequest implements EventRequest {
   
   private QName qn;

   /**
    * @return the qn
    */
   public QName getQn() {
      return qn;
   }

   /**
    * @param qn the qn to set
    */
   public void setQn(QName qn) {
      this.qn = qn;
   }

   /* (non-Javadoc)
    * @see javax.portlet.EventRequest#getEvent()
    */
   @Override
   public Event getEvent() {
      return new MockEvent(qn);
   }

}
