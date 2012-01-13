/*
******************************************************************
File: org.mkcl.els.service.IDocumentService.java
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

package org.mkcl.els.service;

import org.mkcl.els.domain.Document;


// TODO: Auto-generated Javadoc
/**
 * The Interface IDocumentService.
 *
 * @author sandeeps
 * @version v1.0.0
 */
public interface IDocumentService extends IGenericService<Document ,Long>{


	/**
	 * Save.
	 *
	 * @param document the document
	 * @return the document
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
//	public Document save (Document document) throws IOException;

	/**
	 * Find by tag.
	 *
	 * @param tag the tag
	 * @return the document
	 */
	//public Document findByTag(String tag);

	/**
	 * Removes the by tag.
	 *
	 * @param tag the tag
	 */
	//public void removeByTag(String tag);



}
