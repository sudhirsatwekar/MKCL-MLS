/*
******************************************************************
File: org.mkcl.els.common.vo.MemberInfo.java
Copyright (c) 2011, sandeeps, ${company}
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

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class MemberInfo.
 *
 * @author sandeeps
 * @version v1.0.0
 */
public class MemberInfo {
	private Long id;
	
	/** The member name. */
	private String firstName;
	
	/** The middle name. */
	private String middleName;
	
	/** The last name. */
	private String lastName;
	
	/** The member constituency. */
	private String constituency;
	
	/** The party. */
	private String party;
	
	/** The gender. */
	private String gender;
	
	/** The marital status. */
	private boolean maritalStatus;
	
	private Integer noOfTerms;
	
	private String birthDate;

	/**
	 * Instantiates a new member info.
	 */
	public MemberInfo() {
		super();
	}

	public MemberInfo(Long id, String firstName, String middleName,
			String lastName, String constituency, String party, String gender,
			boolean maritalStatus, Integer noOfTerms, String birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.constituency = constituency;
		this.party = party;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.noOfTerms = noOfTerms;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getNoOfTerms() {
		return noOfTerms;
	}

	public void setNoOfTerms(Integer noOfTerms) {
		this.noOfTerms = noOfTerms;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	
}
