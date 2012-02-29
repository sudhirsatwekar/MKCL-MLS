package org.mkcl.els.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.mkcl.els.common.vo.MemberBiographyVO;
import org.mkcl.els.common.vo.MemberInfo;
import org.mkcl.els.common.vo.MemberSearchPage;
import org.mkcl.els.domain.Assembly;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class MemberDetailsRepository.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Repository
public class MemberDetailsRepository extends
        BaseRepository<MemberDetails, Long> {

    /** The custom parameter repository. */
    @Autowired
    private CustomParameterRepository customParameterRepository;

    /** The assembly repository. */
    @Autowired
    private AssemblyRepository assemblyRepository;

    /** The assembly role repository. */
    @Autowired
    private AssemblyRoleRepository assemblyRoleRepository;

    /** The member role repository. */
    @Autowired
    private MemberRoleRepository memberRoleRepository;

    /** The jdbc template. */
    private JdbcTemplate jdbcTemplate;

    /** The Constant CRITERIA_NAME. */
    private static final String CRITERIA_NAME = "name";

    /** The Constant CRITERIA_PARTY. */
    private static final String CRITERIA_PARTY = "party";

    /** The Constant CRITERIA_CONSTITUENCY. */
    private static final String CRITERIA_CONSTITUENCY = "constituency";

    /** The Constant CRITERIA_NO_OF_TERMS. */
    private static final String CRITERIA_NO_OF_TERMS = "no_of_terms";

    /** The Constant CRITERIA_BIRTH_DATE. */
    private static final String CRITERIA_BIRTH_DATE = "birth_date";

    /** The Constant CRITERIA_MARITAL_STATUS. */
    private static final String CRITERIA_MARITAL_STATUS = "marital_status";

    /** The Constant CRITERIA_GENDER. */
    private static final String CRITERIA_GENDER = "gender";

    /** The Constant CRITERIA_ALL. */
    private static final String CRITERIA_ALL = "all";

    /** The PAG e_ size. */
    private Integer PAGE_SIZE = 20;

    /** The Constant FETCH_QUERY. */
    private static final String FETCH_QUERY =
            "SELECT member_details.id,first_name, middle_name,last_name,gender,marital_status,"
            + "no_of_terms,birth_date,constituencies.name as constituency,"
            + "parties.name as party FROM member_details JOIN constituencies JOIN parties "
            + "WHERE member_details.constituency=constituencies.id "
            + "and member_details.party_name=parties.id and ";

    /** The Constant COUNT_QUERY. */
    private static final String COUNT_QUERY =
            "SELECT count(*) FROM member_details JOIN constituencies JOIN parties "
            + "WHERE member_details.constituency=constituencies.id "
            + "and member_details.party_name=parties.id and ";

    /**
     * Sets the data source.
     *
     * @param dataSource the new data source
     */
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Update member personal details.
     *
     * @param memberPersonalDetails the member personal details
     * @return the int
     * @author sujitas
     * @since v1.0.0
     */
    public int updateMemberPersonalDetails(final MemberDetails memberPersonalDetails) {
        final String insertQuery = "update member_details set photo=:photo,"
                + "title=:title," + "first_name=:firstName,"
                + "middle_name=:middleName," + "last_name=:lastName,"
                + "alias=:alias," + "enable_aliasing=:enableAliasing,"
                + "constituency=:constituency," + "party_name=:partyName,"
                + "father_title=:fatherTitle," + "father_name=:fatherName,"
                + "mother_title=:motherTitle," + "mother_name=:motherName,"
                + "birth_date=:birthDate," + "place_of_birth=:placeOfBirth,"
                + "marital_status=:maritalStatus,"
                + "marriage_date=:marriageDate," + "spouse_name=:spouseName,"
                + "no_of_sons=:noOfSons," + "no_of_daughter=:noOfDaughter,"
                + "educational_qualification=:educationalQualification,"
                + "profession=:profession where id=:id";

        Query query = this.em().createNativeQuery(insertQuery);
        query.setParameter("id", memberPersonalDetails.getId());
        query.setParameter("photo", memberPersonalDetails.getPhoto());
        query.setParameter("title", memberPersonalDetails.getTitle());
        query.setParameter("firstName", memberPersonalDetails.getFirstName());
        query.setParameter("middleName", memberPersonalDetails.getMiddleName());
        query.setParameter("lastName", memberPersonalDetails.getLastName());
        query.setParameter("alias", memberPersonalDetails.getAlias());
        query.setParameter(
                "enableAliasing", memberPersonalDetails.isEnableAliasing());
        query.setParameter("constituency", memberPersonalDetails
                .getConstituency() == null ? null : memberPersonalDetails
                .getConstituency().getId());
        query.setParameter(
                "partyName",
                memberPersonalDetails.getPartyName() == null ? null
                        : memberPersonalDetails.getPartyName().getId());
        query.setParameter(
                "fatherTitle", memberPersonalDetails.getFatherTitle());
        query.setParameter("fatherName", memberPersonalDetails.getFatherName());
        query.setParameter(
                "motherTitle", memberPersonalDetails.getMotherTitle());
        query.setParameter("motherName", memberPersonalDetails.getMotherName());
        query.setParameter("birthDate", memberPersonalDetails.getBirthDate());
        query.setParameter(
                "placeOfBirth", memberPersonalDetails.getPlaceOfBirth());
        query.setParameter(
                "maritalStatus", memberPersonalDetails.isMaritalStatus());
        query.setParameter(
                "marriageDate", memberPersonalDetails.getMarriageDate());
        query.setParameter("spouseName", memberPersonalDetails.getSpouseName());
        query.setParameter("noOfSons", memberPersonalDetails.getNoOfSons());
        query.setParameter(
                "noOfDaughter", memberPersonalDetails.getNoOfDaughter());
        query.setParameter(
                "educationalQualification",
                memberPersonalDetails.getEducationalQualification());
        query.setParameter("profession", memberPersonalDetails.getProfession());
        int noOfRows = query.executeUpdate();
        return noOfRows;
    }

    /**
     * Update member contact details.
     *
     * @param memberContactDetails the member contact details
     * @return the int
     * @author sujitas
     * @since v1.0.0
     */
    public int updateMemberContactDetails(final MemberDetails memberContactDetails) {
        final String insertQuery = "update member_details set email=:email,"
                + "present_address=:presentAddress,"
                + "present_state=:presentState,"
                + "present_district=:presentDistrict,"
                + "present_tehsil=:presentTehsil,"
                + "present_city=:presentCity,"
                + "present_pin_code=:presentPinCode,"
                + "present_telephone=:presentTelephone,"
                + "present_fax=:presentFax," + "present_mobile=:presentMobile,"
                + "address_same_as_above=:addressSameAsAbove,"
                + "permanent_address=:permanentAddress,"
                + "permanent_state=:permanentState,"
                + "permanent_district=:permanentDistrict,"
                + "permanent_tehsil=:permanentTehsil,"
                + "permanent_city=:permanentCity,"
                + "permanent_pin_code=:permanentPinCode,"
                + "permanent_telephone=:permanentTelephone,"
                + "permanent_fax=:permanentFax,"
                + "permanent_mobile=:permanentMobile where id=:id";

        Query query = this.em().createNativeQuery(insertQuery);
        query.setParameter("id", memberContactDetails.getId());
        query.setParameter("email", memberContactDetails.getEmail());
        query.setParameter(
                "presentAddress", memberContactDetails.getPresentAddress());
        query.setParameter("presentState", memberContactDetails
                .getPresentState() == null ? null : memberContactDetails
                .getPresentState().getId());
        query.setParameter("presentDistrict", memberContactDetails
                .getPresentDistrict() == null ? null : memberContactDetails
                .getPresentDistrict().getId());
        query.setParameter("presentTehsil", memberContactDetails
                .getPresentTehsil() == null ? null : memberContactDetails
                .getPresentTehsil().getId());
        query.setParameter("presentCity", memberContactDetails.getPresentCity());
        query.setParameter(
                "presentPinCode", memberContactDetails.getPresentPinCode());
        query.setParameter(
                "presentTelephone", memberContactDetails.getPresentTelephone());
        query.setParameter("presentFax", memberContactDetails.getPresentFax());
        query.setParameter(
                "presentMobile", memberContactDetails.getPresentMobile());
        query.setParameter(
                "addressSameAsAbove",
                memberContactDetails.isAddressSameAsAbove());
        if (memberContactDetails.isAddressSameAsAbove()) {
            query.setParameter(
                    "permanentAddress",
                    memberContactDetails.getPresentAddress());
            query.setParameter("permanentState", memberContactDetails
                    .getPresentState() == null ? null : memberContactDetails
                    .getPresentState().getId());
            query.setParameter("permanentDistrict", memberContactDetails
                    .getPresentDistrict() == null ? null : memberContactDetails
                    .getPresentDistrict().getId());
            query.setParameter("permanentTehsil", memberContactDetails
                    .getPresentTehsil() == null ? null : memberContactDetails
                    .getPresentTehsil().getId());
            query.setParameter(
                    "permanentCity", memberContactDetails.getPresentCity());
            query.setParameter(
                    "permanentPinCode",
                    memberContactDetails.getPresentPinCode());
            query.setParameter(
                    "permanentTelephone",
                    memberContactDetails.getPresentTelephone());
            query.setParameter(
                    "permanentFax", memberContactDetails.getPresentFax());
            query.setParameter(
                    "permanentMobile", memberContactDetails.getPresentMobile());
        } else {
            query.setParameter(
                    "permanentAddress",
                    memberContactDetails.getPermanentAddress());
            query.setParameter("permanentState", memberContactDetails
                    .getPermanentState() == null ? null : memberContactDetails
                    .getPermanentState().getId());
            query.setParameter("permanentDistrict", memberContactDetails
                    .getPermanentDistrict() == null ? null
                    : memberContactDetails.getPermanentDistrict().getId());
            query.setParameter("permanentTehsil", memberContactDetails
                    .getPermanentTehsil() == null ? null : memberContactDetails
                    .getPermanentTehsil().getId());
            query.setParameter(
                    "permanentCity", memberContactDetails.getPermanentCity());
            query.setParameter(
                    "permanentPinCode",
                    memberContactDetails.getPermanentPinCode());
            query.setParameter(
                    "permanentTelephone",
                    memberContactDetails.getPermanentTelephone());
            query.setParameter(
                    "permanentFax", memberContactDetails.getPermanentFax());
            query.setParameter(
                    "permanentMobile",
                    memberContactDetails.getPermanentMobile());
        }

        int noOfRows = query.executeUpdate();
        return noOfRows;
    }

    /**
     * Update member other details.
     *
     * @param memberOtherDetails the member other details
     * @return the int
     * @author sujitas
     * @since v1.0.0
     */
    public int updateMemberOtherDetails(final MemberDetails memberOtherDetails) {
        String insertQuery = "update member_details set no_of_terms=:noOfTerms,"
                + "socio_cultural_activities=:socioCulturalActivities,"
                + "literary_artistic_sc_accomplishment=:literaryArtisticScAccomplishment,"
                + "books_published=:booksPublished,"
                + "special_interests=:specialInterests,"
                + "pastime_recreation=:pastimeRecreation,"
                + "sports_clubs=:sportsClubs,"
                + "countries_visited=:countriesVisited,"
                + "experience=:experience,"
                + "other_info=:otherInfo where id=:id";
        Query query = this.em().createNativeQuery(insertQuery);
        query.setParameter("id", memberOtherDetails.getId());
        query.setParameter("noOfTerms", memberOtherDetails.getNoOfTerms());
        query.setParameter(
                "socioCulturalActivities",
                memberOtherDetails.getSocioCulturalActivities());
        query.setParameter(
                "literaryArtisticScAccomplishment",
                memberOtherDetails.getLiteraryArtisticScAccomplishment());
        query.setParameter(
                "booksPublished", memberOtherDetails.getBooksPublished());
        query.setParameter(
                "specialInterests", memberOtherDetails.getSpecialInterests());
        query.setParameter(
                "pastimeRecreation", memberOtherDetails.getPastimeRecreation());
        query.setParameter("sportsClubs", memberOtherDetails.getSportsClubs());
        query.setParameter(
                "countriesVisited", memberOtherDetails.getCountriesVisited());
        query.setParameter("experience", memberOtherDetails.getExperience());
        query.setParameter("otherInfo", memberOtherDetails.getOtherInfo());
        int noOfRows = query.executeUpdate();
        return noOfRows;
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public MemberSearchPage searchMemberDetails(final String criteria1,
                                                final String locale) {
        return searchMemberDetails(criteria1, CRITERIA_ALL, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public MemberSearchPage searchMemberDetails(final String criteria1,
                                                String criteria2,
                                                final String locale) {
        String searchCriteria = null;
        String searchCount = " member_details.locale ='" + locale
                + "' ORDER BY member_details.first_name asc";

        if (criteria2.isEmpty()) {
            criteria2 = CRITERIA_ALL;
        }
        if (criteria1.equals(CRITERIA_NAME)) {
            searchCriteria = " member_details.locale ='" + locale
                    + "' ORDER BY member_details.first_name asc";
        } else if (criteria1.equals(CRITERIA_CONSTITUENCY)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='" + locale
                        + "' ORDER BY constituencies.name asc";
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and constituencies.name='" + criteria2 + "'";
            }
        } else if (criteria1.equals(CRITERIA_PARTY)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY parties.name,member_details.first_name asc";
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and parties.name='" + criteria2
                        + "' ORDER BY member_details.first_name asc";
            }
        } else if (criteria1.equals(CRITERIA_GENDER)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.gender,member_details.first_name asc";
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.gender='" + criteria2
                        + "' ORDER BY member_details.first_name asc";
            }
        } else if (criteria1.equals(CRITERIA_MARITAL_STATUS)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.marital_status,member_details.first_name asc";
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.marital_status=" + criteria2
                        + " ORDER BY member_details.first_name asc";
            }
        } else if (criteria1.equals(CRITERIA_NO_OF_TERMS)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.no_of_terms,member_details.first_name asc";
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.no_of_terms='" + criteria2
                        + "' ORDER BY member_details.first_name asc";
            }
        } else if (criteria1.equals(CRITERIA_BIRTH_DATE)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.birth_date,member_details.first_name asc";
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.birth_date='" + criteria2
                        + "' ORDER BY member_details.first_name asc";
            }
        } else if (criteria1.equals(CRITERIA_ALL)) {
            searchCriteria = " member_details.locale ='" + locale
                    + "' ORDER BY member_details.first_name asc";
        }

        RowMapper<MemberInfo> mapper = new RowMapper<MemberInfo>() {

            @Override
            public MemberInfo mapRow(final ResultSet rs, final int arg1)
                    throws SQLException {
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setId(rs.getLong("id"));
                memberInfo.setFirstName(rs.getString("first_name"));
                memberInfo.setMiddleName(rs.getString("middle_name"));
                memberInfo.setLastName(rs.getString("last_name"));
                memberInfo.setConstituency(rs.getString("constituency"));
                memberInfo.setParty(rs.getString("party"));
                memberInfo.setGender(rs.getString("gender"));
                memberInfo.setMaritalStatus(rs.getBoolean("marital_status"));
                memberInfo.setNoOfTerms(rs.getInt("no_of_terms"));
                memberInfo.setBirthDate(rs.getString("birth_date"));
                return memberInfo;
            }
        };
        MemberSearchPage searchPage = new MemberSearchPage();
        searchPage.setPageItems(jdbcTemplate.query(
                FETCH_QUERY + searchCriteria, mapper, new Object[] {}));
        if (searchCriteria.contains("ORDER BY")) {
            searchCriteria = searchCriteria.split("ORDER BY")[0];
        }
        searchPage.setTotalRecords(jdbcTemplate.queryForInt(COUNT_QUERY
                + searchCount));
        return searchPage;
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param page the page
     * @param rows the rows
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public MemberSearchPage searchMemberDetails(final String criteria1,
                                                final int page,
                                                final int rows,
                                                final String locale) {
        return searchMemberDetails(criteria1, CRITERIA_ALL, page, rows, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param page the page
     * @param rows the rows
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public MemberSearchPage searchMemberDetails(final String criteria1,
                                                String criteria2,
                                                final int page,
                                                final int rows,
                                                final String locale) {
        this.PAGE_SIZE = rows;
        int firstIndex = (page - 1) * PAGE_SIZE;
        String searchCriteria = null;
        if (criteria2.isEmpty()) {
            criteria2 = CRITERIA_ALL;
        }
        if (criteria1.equals(CRITERIA_NAME)) {
            searchCriteria = " member_details.locale ='" + locale
                    + "' ORDER BY member_details.first_name asc LIMIT "
                    + firstIndex + "," + PAGE_SIZE;
        } else if (criteria1.equals(CRITERIA_CONSTITUENCY)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='" + locale
                        + "' ORDER BY constituencies.name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and constituencies.name='" + criteria2
                        + "' LIMIT " + firstIndex + "," + PAGE_SIZE;
            }

        } else if (criteria1.equals(CRITERIA_PARTY)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY parties.name,member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and parties.name='" + criteria2
                        + "' ORDER BY member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            }
        } else if (criteria1.equals(CRITERIA_GENDER)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.gender,member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.gender='" + criteria2
                        + "' ORDER BY member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            }
        } else if (criteria1.equals(CRITERIA_MARITAL_STATUS)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.marital_status,"
                        + "member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.marital_status=" + criteria2
                        + " ORDER BY member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            }
        } else if (criteria1.equals(CRITERIA_NO_OF_TERMS)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.no_of_terms,"
                        + "member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.no_of_terms='" + criteria2
                        + "' ORDER BY member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            }
        } else if (criteria1.equals(CRITERIA_BIRTH_DATE)) {
            if (criteria2.equals(CRITERIA_ALL)) {
                searchCriteria = " member_details.locale ='"
                        + locale
                        + "' ORDER BY member_details.birth_date,"
                        + "member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            } else {
                searchCriteria = " member_details.locale ='" + locale
                        + "' and member_details.birth_date='" + criteria2
                        + "' ORDER BY member_details.first_name asc LIMIT "
                        + firstIndex + "," + PAGE_SIZE;
            }
        } else if (criteria1.equals(CRITERIA_ALL)) {
            searchCriteria = " member_details.locale ='" + locale
                    + "' ORDER BY member_details.first_name asc LIMIT "
                    + firstIndex + "," + PAGE_SIZE;
        }
        RowMapper<MemberInfo> mapper = new RowMapper<MemberInfo>() {

            @Override
            public MemberInfo mapRow(final ResultSet rs, final int rowNo)
                    throws SQLException {
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setId(rs.getLong("id"));
                memberInfo.setFirstName(rs.getString("first_name"));
                memberInfo.setMiddleName(rs.getString("middle_name"));
                memberInfo.setLastName(rs.getString("last_name"));
                memberInfo.setConstituency(rs.getString("constituency"));
                memberInfo.setParty(rs.getString("party"));
                memberInfo.setGender(rs.getString("gender"));
                memberInfo.setMaritalStatus(rs.getBoolean("marital_status"));
                memberInfo.setNoOfTerms(rs.getInt("no_of_terms"));
                memberInfo.setBirthDate(rs.getString("birth_date"));
                return memberInfo;
            }
        };
        MemberSearchPage searchPage = new MemberSearchPage();
        searchPage.setPageItems(jdbcTemplate.query(
                FETCH_QUERY + searchCriteria, mapper, new Object[] {}));
        System.out.println(FETCH_QUERY + searchCriteria);
        if (searchCriteria.contains("ORDER BY")) {
            searchCriteria = searchCriteria.split("ORDER BY")[0];
        }
        searchPage.setTotalRecords(jdbcTemplate.queryForInt(COUNT_QUERY
                + searchCriteria));
        System.out.println(COUNT_QUERY + searchCriteria);
        return searchPage;
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param criteria3 the criteria3
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public MemberSearchPage searchMemberDetails(final String criteria1,
                                                final String criteria2,
                                                final String criteria3,
                                                final String locale) {
        return null;
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param criteria3 the criteria3
     * @param page the page
     * @param rows the rows
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public MemberSearchPage searchMemberDetails(final String criteria1,
                                                final String criteria2,
                                                final String criteria3,
                                                final int page,
                                                final int rows,
                                                final String locale) {
        return null;
    }

    /**
     * Max no of terms.
     *
     * @param locale the locale
     * @return the integer
     * @author sujitas
     * @since v1.0.0
     */
    public Integer maxNoOfTerms(final String locale) {
        String query = "SELECT max(no_of_terms) FROM member_details WHERE locale = '"
                + locale + "'";
        return jdbcTemplate.queryForInt(query);
    }

    /**
     * Find by id locale.
     *
     * @param id the id
     * @param locale the locale
     * @return the member details
     * @author sujitas
     * @since v1.0.0
     */
    public MemberDetails findByIdLocale(final long id, final String locale) {
        Search search = new Search();
        search.addFilterEqual("locale", locale);
        search.addFilterEqual("id", id);
        return this.searchUnique(search);
    }

    /**
     * Find biography.
     *
     * @param id the id
     * @param locale the locale
     * @return the member biography vo
     * @author sujitas
     * @since v1.0.0
     */
    public MemberBiographyVO findBiography(final long id, final String locale) {
        MemberDetails memberDetails = findByIdLocale(id, locale);
        MemberBiographyVO memberBiographyVO = new MemberBiographyVO();
        if (memberDetails != null) {
            memberBiographyVO.setAlias(memberDetails.getAlias());
            memberBiographyVO.setBirthDate(memberDetails.getBirthDate().toString());
            memberBiographyVO.setBooksPublished(memberDetails
                    .getBooksPublished());
            memberBiographyVO
                    .setConstituency(memberDetails.getConstituency() != null ? memberDetails
                            .getConstituency().getName() : "");
            memberBiographyVO.setCountriesVisited(memberDetails
                    .getCountriesVisited());
            memberBiographyVO.setEducationalQualification(memberDetails
                    .getEducationalQualification());
            memberBiographyVO.setEmail(memberDetails.getEmail());
            memberBiographyVO.setEnableAliasing(memberDetails
                    .isEnableAliasing());
            memberBiographyVO.setExperience(memberDetails.getExperience());
            memberBiographyVO.setFatherName(memberDetails.getFatherName());
            memberBiographyVO.setFirstName(memberDetails.getFirstName());
            memberBiographyVO.setGender(memberDetails.getGender());
            memberBiographyVO.setId(memberDetails.getId());
            memberBiographyVO.setLastName(memberDetails.getLastName());
            memberBiographyVO
                    .setLiteraryArtisticScAccomplishment(memberBiographyVO
                            .getLiteraryArtisticScAccomplishment());
            memberBiographyVO.setLocale(memberDetails.getLocale());
            memberBiographyVO
                    .setMaritalStatus(memberDetails.isMaritalStatus() == true ? "married"
                            : "unmarried");
            memberBiographyVO.setMarriageDate(memberDetails.getMarriageDate());
            memberBiographyVO.setPositionDetails(memberDetails.getPositionDetails());
            memberBiographyVO.setMiddleName(memberDetails.getMiddleName());
            memberBiographyVO.setMotherName(memberDetails.getMotherName());
            memberBiographyVO.setNoOfDaughter(memberDetails.getNoOfDaughter());
            memberBiographyVO.setNoOfTerms(memberDetails.getNoOfTerms());
            memberBiographyVO.setOtherInfo(memberDetails.getOtherInfo());
            memberBiographyVO
                    .setPartyName(memberDetails.getPartyName() != null ? memberDetails
                            .getPartyName().getName() : "");
            memberBiographyVO
                    .setPartyFlag(memberDetails.getPartyName() != null ? memberDetails
                            .getPartyName().getPhoto() : "");
            memberBiographyVO.setPastimeRecreation(memberDetails
                    .getPastimeRecreation());
            memberBiographyVO.setPermanentAddress(memberDetails
                    .getPermanentAddress());
            memberBiographyVO
                    .setPermanentCity(memberDetails.getPermanentCity());
            memberBiographyVO.setPermanentDistrict(memberDetails
                    .getPermanentDistrict() != null ? memberDetails
                    .getPermanentDistrict().getName() : "");
            memberBiographyVO.setPermanentFax(memberDetails.getPermanentFax());
            memberBiographyVO.setPermanentMobile(memberDetails
                    .getPermanentMobile());
            memberBiographyVO.setPermanentPinCode(memberDetails
                    .getPermanentPinCode());
            memberBiographyVO.setPermanentState(memberDetails
                    .getPermanentState() != null ? memberDetails
                    .getPermanentState().getName() : "");
            memberBiographyVO.setPermanentTehsil(memberDetails
                    .getPermanentTehsil() != null ? memberDetails
                    .getPermanentTehsil().getName() : "");
            memberBiographyVO.setPermanentTelephone(memberDetails
                    .getPermanentTelephone());
            memberBiographyVO.setPlaceOfBirth(memberDetails.getPlaceOfBirth());
            memberBiographyVO.setPresentAddress(memberDetails
                    .getPresentAddress());
            memberBiographyVO.setPresentCity(memberDetails.getPresentCity());
            memberBiographyVO.setPresentDistrict(memberDetails
                    .getPresentDistrict() != null ? memberDetails
                    .getPresentDistrict().getName() : "");
            memberBiographyVO.setPresentFax(memberDetails.getPresentFax());
            memberBiographyVO
                    .setPresentMobile(memberDetails.getPresentMobile());
            memberBiographyVO.setPresentPinCode(memberDetails
                    .getPresentPinCode());
            memberBiographyVO
                    .setPresentState(memberDetails.getPresentState() != null ? memberDetails
                            .getPresentState().getName() : "");
            memberBiographyVO
                    .setPresentTehsil(memberDetails.getPresentTehsil() != null ? memberDetails
                            .getPresentTehsil().getName() : "");
            memberBiographyVO.setPresentTelephone(memberDetails
                    .getPresentTelephone());
            memberBiographyVO.setPhoto(memberDetails.getPhoto());
            memberBiographyVO.setProfession(memberDetails.getProfession());
            memberBiographyVO.setSocioCulturalActivities(memberDetails
                    .getSocioCulturalActivities());
            memberBiographyVO.setSpecialInterests(memberDetails
                    .getSpecialInterests());
            memberBiographyVO.setSportsClubs(memberDetails.getSportsClubs());
            memberBiographyVO.setSpouseName(memberDetails.getSpouseName());
            memberBiographyVO.setTitle(memberDetails.getTitle());
        }
        return memberBiographyVO;
    }

    // public Document getPhoto(String tag) {
    // return documentRepository.findByTag(tag);
    // }

    /**
     * Find by id and locale.
     *
     * @param memberId the member id
     * @param locale the locale
     * @return the member details
     * @author sujitas
     * @since v1.0.0
     */
    public MemberDetails findByIdAndLocale(final Long memberId,
                                           final String locale) {
        Search search = new Search();
        search.addFilterEqual("id", memberId);
        search.addFilterEqual("locale", locale);
        return this.searchUnique(search);
    }

    /**
     * Creates the member and default role.
     *
     * @param memberPersonalDetails the member personal details
     * @author sujitas
     * @since v1.0.0
     */
    public void createMemberAndDefaultRole(final MemberDetails memberPersonalDetails) {
        this.persist(memberPersonalDetails);
        MemberRole memberRole = new MemberRole();
        String locale = memberPersonalDetails.getLocale();
        Assembly assembly = assemblyRepository.findCurrentAssembly(locale);
        memberRole.setAssembly(assembly);
        memberRole.setFromDate(assembly.getAssemblyStartDate());
        memberRole.setToDate(assembly.getAssemblyEndDate());
        memberRole.setLocale(locale);
        memberRole.setMember(memberPersonalDetails);
        memberRole.setRemarks("");
        memberRole.setRole(assemblyRoleRepository.findByNameAndLocale(
                customParameterRepository.findByName("DEFAULT_MEMBERROLE")
                        .getValue(), locale));
        memberRole.setStatus(customParameterRepository.findByName(
                "MEMBERROLE_ASSIGNED").getValue());
        memberRoleRepository.persist(memberRole);
    }
}
