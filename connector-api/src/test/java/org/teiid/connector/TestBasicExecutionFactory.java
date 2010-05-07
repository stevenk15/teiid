/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package org.teiid.connector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;
import org.teiid.resource.adapter.BasicExecutionFactory;
import org.teiid.resource.cci.ConnectorCapabilities;
import org.teiid.resource.cci.ExecutionFactory;


public class TestBasicExecutionFactory {

    @Test public void testConnectorCapabilitiesOverride() throws Exception {

    	ExecutionFactory c = new BasicExecutionFactory();
    	//Mockito.stub(c.getCapabilities()).toReturn(new BasicConnectorCapabilities());
    	
    	ConnectorCapabilities caps = c.getCapabilities();
    	assertFalse(caps.supportsExistsCriteria());
    	assertFalse(caps.supportsExcept());

    	
    	c = new BasicExecutionFactory() {
    		@Override
    		public Properties getOverrideCapabilities() {
    	    	Properties props = new Properties();
    	    	props.setProperty("supportsExistsCriteria", "true"); //$NON-NLS-1$ //$NON-NLS-2$
    	    	props.setProperty("supportsExcept", "true"); //$NON-NLS-1$ //$NON-NLS-2$
    	    	return props;
    		}
    	};
    	
    	caps = c.getCapabilities();
    	assertTrue(caps.supportsExistsCriteria());
    	assertTrue(caps.supportsExcept());
    }
}