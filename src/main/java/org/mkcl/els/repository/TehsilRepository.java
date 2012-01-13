/*
******************************************************************
File: org.mkcl.els.repository.TehsilRepository.java
Copyright (c) 2011, amitd, MKCL
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
package org.mkcl.els.repository;

import java.util.List;

import org.mkcl.els.domain.Tehsil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class TehsilRepository.
 *
 * @author amitd
 * @version v1.0.0
 */
@Repository
public class TehsilRepository extends BaseRepository<Tehsil, Long> {

    /** The district repository. */
    @Autowired
    private DistrictRepository districtRepository;

    /**
     * Find by tehsil name.
     *
     * @param name the name
     * @return the tehsil
     */
    public Tehsil findByTehsilName(final String name) {
        Search search = new Search();
        search.addFilterEqual("name", name);
        Tehsil tehsil = this.searchUnique(search);
        return tehsil;
        }

    /**
     * Find tehsils by district name.
     *
     * @param name the name
     * @return the list
     */
    public List<Tehsil> findTehsilsByDistrictName(final String name) {
        Search search = new Search();
        search.addFilterEqual("district", districtRepository.findByName(name));
        search.addSort("name", false);
        List<Tehsil> tehsils = this.search(search);
        return tehsils;
        }

    /**
     * Find tehsils by district id.
     *
     * @param districtId the district id
     * @return the list
     */
    public List<Tehsil> findTehsilsByDistrictId(final Long districtId) {
        Search search = new Search();
        search.addFilterEqual("district", districtRepository.find(districtId));
        search.addSort("name", false);
        List<Tehsil> tehsils = this.search(search);
        return tehsils;
        }
    }
