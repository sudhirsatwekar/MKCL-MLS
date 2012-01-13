/*
******************************************************************
File: org.mkcl.els.common.vo.Rule.java
Copyright (c) 2011, vishals, MKCL
All rights reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

******************************************************************
 */
package org.mkcl.els.common.vo;

/**
 * The Class Rule.
 *
 * @author vishals
 * @version v1.0.0
 */
public class Rule {
	
	/** The field. */
	private String field;
	
	/** The op. */
	private String op;
	
	/** The data. */
	private String data;
	
	
	/**
	 * Instantiates a new rule.
	 */
	public Rule(){
	}

	/**
	 * Instantiates a new rule.
	 *
	 * @param field the field
	 * @param op the op
	 * @param data the data
	 */
	public Rule(String field, String op, String data) {
		super();
		this.field = field;
		this.op = op;
		this.data = data;
	}

	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Sets the field.
	 *
	 * @param field the new field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	public String getOp() {
		return op;
	}

	/**
	 * Sets the op.
	 *
	 * @param op the new op
	 */
	public void setOp(String op) {
		this.op = op;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

}
