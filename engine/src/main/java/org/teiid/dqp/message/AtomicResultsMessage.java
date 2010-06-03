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

package org.teiid.dqp.message;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import org.teiid.client.BatchSerializer;
import org.teiid.core.util.ExternalizeUtil;


public class AtomicResultsMessage implements Externalizable {

	private List[] results;
	private String[] dataTypes;

    // Final row index in complete result set, if known
    private int finalRow = -1;
    
    // by default we support implicit close.
    private boolean supportsImplicitClose = true;

    private boolean isTransactional;
    
    private List<Exception> warnings;

    // to honor the externalizable contract
	public AtomicResultsMessage() {
	}
	
	public AtomicResultsMessage(List[] results, String[] dataTypes) {
		this.dataTypes = dataTypes;
        this.results = results;
	}
	
    public boolean supportsImplicitClose() {
        return this.supportsImplicitClose;
    }
    
    public void setSupportsImplicitClose(boolean supportsImplicitClose) {
        this.supportsImplicitClose = supportsImplicitClose;
    }    
    
    public int getFinalRow() {
        return finalRow;
    }
    
    public void setFinalRow(int i) {
        finalRow = i;
    }

	public List[] getResults() {
		return results;
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        dataTypes = ExternalizeUtil.readStringArray(in);
        results = BatchSerializer.readBatch(in, dataTypes);
        finalRow = in.readInt();
        supportsImplicitClose = in.readBoolean();
        warnings = (List<Exception>)in.readObject();
        isTransactional = in.readBoolean();
	}

	public void writeExternal(ObjectOutput out) throws IOException {
        ExternalizeUtil.writeArray(out, dataTypes);
        BatchSerializer.writeBatch(out, dataTypes, results);
        out.writeInt(finalRow);
        out.writeBoolean(supportsImplicitClose);
        out.writeObject(warnings);
        out.writeBoolean(isTransactional);
	}

	public boolean isTransactional() {
		return isTransactional;
	}

	public void setTransactional(boolean isTransactional) {
		this.isTransactional = isTransactional;
	}   
	
	public void setWarnings(List<Exception> warnings) {
		this.warnings = warnings;
	}
	
	public List<Exception> getWarnings() {
		return warnings;
	}
}