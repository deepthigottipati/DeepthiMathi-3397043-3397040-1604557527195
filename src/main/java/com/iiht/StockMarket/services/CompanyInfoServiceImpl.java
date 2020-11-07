package com.iiht.StockMarket.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;

@Service
@Transactional
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	@Autowired
    private CompanyInfoRepository repository; 
    
	
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
        repository.save(StockMarketUtility.convertToCompanyDetails(companyDetailsDTO));
        return companyDetailsDTO;
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO deleteCompany(Long companyCode) {
        repository.deleteByCompanyCode(companyCode);
        return StockMarketUtility.convertToCompanyDetailsDTO(repository.findCompanyDetailsById(companyCode));
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		return StockMarketUtility.convertToCompanyDetailsDTO(repository.findCompanyDetailsById(companyCode));
	};
	
	//----------------------------------------------------------------------------
	public List<CompanyDetailsDTO> getAllCompanies() {
		return StockMarketUtility.convertToCompanyDetailsDtoList(repository.findAll());
	};
}