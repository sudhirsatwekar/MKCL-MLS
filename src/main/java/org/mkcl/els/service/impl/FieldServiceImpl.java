package org.mkcl.els.service.impl;

import org.mkcl.els.domain.Field;
import org.mkcl.els.service.IFieldService;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl extends GenericServiceImpl<Field,Long>
implements IFieldService {

//	private FieldRepository fieldRepository;
//
//	@Autowired
//	public void setFieldRepository(
//			final FieldRepository fieldRepository) {
//		this.dao = fieldRepository;
//		this.fieldRepository = fieldRepository;
//	}
//
//	@Override
//	public Field findByName(final String name) {
//		return fieldRepository.findByName(name);
//	}
//
//	@Override
//	public List<Field> findByFormNameSorted(final String formName) {
//		return fieldRepository.findByFormNameSorted(formName);
//	}

}
