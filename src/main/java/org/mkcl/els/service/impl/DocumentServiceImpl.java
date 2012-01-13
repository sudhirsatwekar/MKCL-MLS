/*
******************************************************************
File: org.mkcl.els.service.impl.DocumentServiceImpl.java
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

import org.mkcl.els.domain.Document;
import org.mkcl.els.service.IDocumentService;
import org.springframework.stereotype.Service;


// TODO: Auto-generated Javadoc
/**
 * The Class DocumentServiceImpl.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Service
public class DocumentServiceImpl extends GenericServiceImpl<Document,Long> implements IDocumentService {

//	/** The document repository. */
//	@Autowired
//	DocumentRepository documentRepository;
//
//	/* (non-Javadoc)
//	 * @see org.mkcl.els.service.IDocumentService#save(org.mkcl.els.domain.Document)
//	 */
//	@Override
//	@Transactional
//	public Document save(final Document document) throws IOException {
//		return documentRepository.save(document);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.mkcl.els.service.IDocumentService#findByTag(java.lang.String)
//	 */
//	@Override
//	public Document findByTag(String tag) {
//		return documentRepository.findByTag(tag);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.mkcl.els.service.IDocumentService#removeByTag(java.lang.String)
//	 */
//	@Override
//	public void removeByTag(String tag) {
//		documentRepository.removeByTag(tag);
//	}

}
