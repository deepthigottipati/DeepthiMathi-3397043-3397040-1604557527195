package com.iiht.StockMarket.services;


import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;
import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.repository.StockPriceRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
    private StockPriceRepository stockRepository;

	@Autowired
    private CompanyInfoRepository companyRepository;
	
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
        stockRepository.save(StockMarketUtility.convertToStockPriceDetails(stockPriceDetailsDTO));
		return stockPriceDetailsDTO;
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> deleteStock(Long companyCode) {
        stockRepository.deleteById(companyCode);
        return StockMarketUtility.convertToStockPriceDetailsDtoList(stockRepository.findStockByCompanyCode(companyCode));
	};
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode){
		return StockMarketUtility.convertToStockPriceDetailsDtoList(stockRepository.findStockByCompanyCode(companyCode));
	};
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO getStockPriceDetailsDTO(StockPriceDetails stockDetails)	{
        return StockMarketUtility.convertToStockPriceDetailsDTO(stockDetails);
	};
	//---------------------------------------------------------------------
	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMaxStockPrice(companyCode, startDate, endDate);
	}
	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findAvgStockPrice(companyCode, startDate, endDate);
	}
	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMinStockPrice(companyCode, startDate, endDate);
	}
	
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {
		
        StockPriceIndexDTO dto = new StockPriceIndexDTO();
        dto.setAvgStockPrice(stockRepository.findAvgStockPrice(companyCode, startDate, endDate));
        dto.setCompanyDto(StockMarketUtility.convertToCompanyDetailsDTO(companyRepository.findCompanyDetailsById(companyCode)));
        dto.setMaxStockPrice(stockRepository.findMaxStockPrice(companyCode, startDate, endDate));
        dto.setMinStockPrice(stockRepository.findMinStockPrice(companyCode, startDate, endDate));
        dto.setStockPriceList(StockMarketUtility.convertToStockPriceDetailsDtoList(stockRepository.findStockByCompanyCode(companyCode)));
        return dto;

    
}}