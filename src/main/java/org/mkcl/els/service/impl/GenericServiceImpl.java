/*
******************************************************************
File: org.mkcl.els.service.impl.GenericServiceImpl.java
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
package org.mkcl.els.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mkcl.els.common.exception.RecordNotFoundException;
import org.mkcl.els.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import com.trg.dao.jpa.GenericDAO;

/**
 * The Class GenericServiceImpl.
 *
 * @param <T> the generic type
 * @param <PK> the generic type
 * @author vishals
 * @version v1.0.0
 */
public  class GenericServiceImpl<T, PK extends Serializable> implements IGenericService<T, PK>{

	/** The dao. */
	protected GenericDAO<T, PK> dao;
	
	/**
	 * Instantiates a new generic service impl.
	 */
	protected GenericServiceImpl() {
    }

    /**
     * Instantiates a new generic service impl.
     *
     * @param genericDao the generic dao
     */
    protected GenericServiceImpl(GenericDAO<T, PK> genericDao) {
        this.dao = genericDao;
    }
	
	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#findAll()
	 */
	public List<T> findAll() {
		return dao.findAll();
	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#findById(java.io.Serializable)
	 */
	public T findById(PK id) {
		T entity = dao.find(id);
		if(entity==null){
				throw new RecordNotFoundException("Error: Record was not found for Entity with Id:" + id);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#exists(java.io.Serializable)
	 */
	public boolean exists(PK id) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#create(java.lang.Object)
	 */
	public T create(T object) {
		//T saved_object = dao.save(object);
		//dao.flush();
		return dao.save(object);
	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#update(java.lang.Object)
	 */
	public T update(T object) {
		return dao.merge(object);
	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#remove(java.lang.Object)
	 */
	public void remove(T object) {
		dao.remove(object);
	}

	/* (non-Javadoc)
	 * @see org.mkcl.els.service.IGenericService#removeById(java.io.Serializable)
	 */
	public void removeById(PK id) {
		dao.removeById(id);
	}

}
