/*
******************************************************************
File: org.mkcl.els.service.impl.StateServiceImpl.java
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
package org.mkcl.els.service.impl;

import java.util.List;

import org.mkcl.els.domain.State;
import org.mkcl.els.repository.StateRepository;
import org.mkcl.els.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class StateServiceImpl.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Service
public class StateServiceImpl extends GenericServiceImpl<State,Long> implements IStateService{

	/** The state repository. */
	private StateRepository stateRepository;
	
	/**
	 * Sets the state repository.
	 *
	 * @param stateRepository the new state repository
	 */
//	@Autowired
//	public void setStateRepository(StateRepository stateRepository) 
//	{
//		this.dao = stateRepository;
//		this.stateRepository = stateRepository;
//	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IStateService#findByName(java.lang.String)
	 */
//	@Override
//	public State findByName(String name) {
//		return stateRepository.findByName(name);
//	}
//
//	@Override
//	public List<State> findAllSorted() {
//		return stateRepository.findAllSorted();
//	}
//
//	@Override
//	public List<State> findAllSorted(String property, boolean descOrder) {
//		return stateRepository.findAllSorted(property, descOrder);
//	}
}
