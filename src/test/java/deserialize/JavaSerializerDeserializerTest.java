package deserialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JavaSerializerDeserializerTest {

    @Test
    public void test_deserialize() {
        // arrange
        String input = "ACED000573720037636F6D2E6169732E70726F6A6563742E73656375726974792E437573746F6D52616E646F6D4163636573735265666572656E63654D617086AF47C96F31B9F4020000787200346F72672E6F776173702E65736170692E7265666572656E63652E41627374726163744163636573735265666572656E63654D617003502F4779E21FF60200024C000464746F6974000F4C6A6176612F7574696C2F4D61703B4C000469746F6471007E00027870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700001887B7371007E000605E9CEAB787371007E00043F4000000000000C7708000000100000000171007E000971007E000878";

        // act
        MerchantEntity merchant = (MerchantEntity) JavaSerializerDeserializer.deserialize(hexStringToByteArray(input));
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    private static class MerchantEntity extends PermissionEntity {

        private static final long serialVersionUID = 1L;

        /**/
        private String isMerchantPage = "OPP";

        private Integer id;

        private String isHktvOrHkb;

        private String userCode;

        private String merchantCode;
        /**/
        private String merchantName;
        /**/
        private String merchantNameTchi;

        private String brNo;

        private String companyAddress;
        /**/
        private String address1;
        /**/
        private String address2;
        /**/
        private String address3;
        /**/
        private String address4;
        private String rmCode;

        private String contactPersonPhone;
        /**/
        // private String contactPersonInt;
        private String contactPersonExt;
        private String contactPersonFax;

        private String merchantNameSchi;
        private String contactEmail;
        /**/
        private String placeOfIncorporation;

        //private String cINo;

        private String registeredOffice;


        private String contractType;
        /**/
        /**/
        private String relatedLicense;
        /**/
        private String leadDesc;
        /**/
        private String noOfProduct;
        /**/
        private String shopExp;
        /**/
        private String vipCode;
        /**/
        private String groupName;
        /**/
        private Integer leadId;
        /**/
        private String createdBy;
        /**/
        private Date createdDate;
        /**/
        private String lastUpdatedBy;
        /**/
        private Date lastUpdatedDate;

        private String reviewStatus;

        private String officeEmail;

        private String description;

        private String merchantGrading;

        private String status = "Accept";

        private Integer maxOppDuration;

        private String storeCode;
        private String storeName;
        private String hybrisStoreid;
        private String storeNameTc;

        private List<ContractTypeEntity> merchantContractType;

        private List<ParmVo> merchantPaymentTerm;

        private List<ParmVo> merchantPaymentGroup;

        private String placeIncorporation;

        private String placeIncorporationString;

        private String departmentCode;

        private String ciNo;

        private Date brExpiryDate;

        private Integer superMerchantId;

        private String merchantToken;

        private String busType;

        private Boolean fullPayment;

        private String expressCategory;

        private Integer storeId;

        private boolean masterContractInd;
    }

    @Data
    private static class ContractTypeEntity implements Serializable {
        private static final long serialVersionUID = 1L;

        /**/
        private Integer id;

        private Integer version;
        /**/
        private String code;
        /**/
        private String shortCode;
        /**/
        private String description;
        /**/
        private String disqSeq;
        /**/
        private Integer busUnitId;

        private Integer merchantId;
        /**/
        private String masterContractInd;
        /**/
        private String defaultPaymentTerms;
        /**/
        private Integer defaultEffectiveYear;
        /**/
        private Integer defaultEffectiveMonth;
        /**/
        private String legalAutoApproveInd;
        /**/
        private String annualFeeInd;
        /**/
        private String depositInd;
        /**/
        private String defaultFile;
        /**/
        private String defaultFileDesc;
        /**/
        private String currency;
        /**/
        private Integer workflowId;
        /**/
        private String workflowName;
        /**/
        private String buUnitCode;
        /**/
        private String createdBy;
        /**/
        private Date createdDate;
        /**/
        private String lastUpdatedBy;
        /**/
        private Date lastUpdatedDate;

        private String mappingGroup;

        private String activeInd;
    }

    @Data
    private static class ParmVo extends BaseVo {
        private boolean onlySearchByPlatform;

        private Set<String> shortDescs;

        private boolean platformSharing;

        private Integer id;

        private String segment;

        private String code;
        private String fullCode;

        private String shortDesc;

        private String longDesc;
        private String shortDescTc;

        private String longDescTc;

        private String value;

        private String value0;

        private String value1;

        private Short dispSeq;

        private String parentId;

        private String segmentCopy;

        private List<ParmVo> listValue0;

        private List<ParmVo> listValue1;

        private byte [] parmFile;

        private String fileName;

        private String parentName;

        private String codeCopy;

        private String lowMarginInd;
        private String parentSegment;
        private String parentCode;
        private String parentLikeCode;

        private List<String> codeList;

        private Integer platformId;
    }

    @Data
    private static class BaseVo {
        private String createdBy;

        private Date createdDate;

        private String lastUpdatedBy;

        private Date lastUpdatedDate;

        private Integer startRow;

        private Integer endRow;

        private Integer dataTotal;

        private Integer pageNum;

        private Integer pageSize;

        private Integer pageStart;

        private Integer totalCount;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    private static class PermissionEntity extends BaseModel implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;
        private Integer merchantId; //user for permisson
        private Integer rmId ;
        private Integer mrId ;
        private Integer rmUserId;  // user for permisson
        private Integer rmLeaderId;//user for permisson
        private Integer deptHeadId;//user for permisson
        private Integer rmOperatorId;
        private Integer roleId;
        private Integer userId;
        private Integer supplierId;
        private Integer storeLevelUserId;
        private Integer superMerchantId;
    }

    @Data
    private static class BaseModel implements Serializable {

        private static final long serialVersionUID = 1L;

        private String createdBy;

        private Date createdDate;

        private String lastUpdatedBy;

        private Date lastUpdatedDate;

        private Integer startRow;

        private Integer endRow;

        private Integer dataTotal;

        private Integer excelRowIndex;
        private Integer limit;

        //page
        private List<? extends BaseModel>  totalItems;
        private Integer pageNum;
        private Integer pageSize;
        private Integer pageStart;
        private Integer totalCount;
    }
}
