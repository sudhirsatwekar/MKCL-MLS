/*
 ******************************************************************
File: org.mkcl.els.service.impl.MessageResourceServiceImpl.java
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

import org.mkcl.els.domain.MessageResource;
import org.mkcl.els.service.IMessageResourceService;
import org.springframework.stereotype.Service;

/**
 * The Class MessageResourceServiceImpl.
 *
 * @author vishals
 * @version v1.0.0
 */
@Service
public class MessageResourceServiceImpl extends
        GenericServiceImpl<MessageResource, Long> implements
        IMessageResourceService {

    // /** The repository. */
    // private MessageResourceRepository messageResourceRepository;
    //
    // /**
    // * Sets the message resource repository.
    // *
    // * @param messageResourceRepository the new message resource repository
    // */
    // @Autowired
    // public void setMessageResourceRepository(final MessageResourceRepository
    // messageResourceRepository) {
    // this.dao = messageResourceRepository;
    // this.messageResourceRepository = messageResourceRepository;
    // }
    //
    //
    // /**
    // * Find a messageResource based on locale & code
    // *
    // * @param locale the locale
    // * @param code the code
    // */
    // @Override
    // public MessageResource findByLocaleAndCode(final String locale, final
    // String code) {
    // return messageResourceRepository.findByLocaleAndCode(locale, code);
    // }

}
