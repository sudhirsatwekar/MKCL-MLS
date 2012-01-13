/*
******************************************************************
File: org.mkcl.els.service.IGenericService.java
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
package org.mkcl.els.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * The Interface IGenericService.
 *
 * @param <T> the generic type
 * @param <PK> the generic type
 * @author vishals
 * @version v1.0.0
 */
public interface IGenericService<T, PK extends Serializable> {
	
	/**
	 * Finds all the records.
	 *
	 * @return the list
	 */
	@Transactional(readOnly=true)
	List<T> findAll();
	
	/**
	 * Finds the record by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	@Transactional(readOnly=true)
	T findById(PK id);
	
	/**
	 * Checks if the record Exists.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Transactional(readOnly=true)
	boolean exists(PK id);
	
	/**
	 * Creates the new record.
	 *
	 * @param object the object
	 * @return the t
	 */
	@Transactional
	T  create(T object);
	
	/**
	 * Updates the existing record.
	 *
	 * @param object the object
	 * @return the t
	 */
	@Transactional
	T update(T object);
	
	/**
	 * Removes the record.
	 *
	 * @param object the object
	 * @return the t
	 */
	@Transactional
	void remove(T object);
	
	/**
	 * Removes the record based on id.
	 *
	 * @param id the id
	 * @return the t
	 */
	@Transactional
	void removeById(PK id);

}
