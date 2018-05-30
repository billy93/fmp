package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByRuleNoCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.RuleQuery;

@Service
public class RuleQueryMapper {
	
	public RuleQuery convertAtpcoRecord2(AtpcoRecord2 record2) {

		RuleQuery result = new RuleQuery();
		
		result.setCxr(record2.getCarrierCode());
		result.setRuleNo(record2.getRuleNo());
		result.setTarNo(record2.getRuleTariffNo());
		result.setSrc("A");
		result.setType("Rule");
		
		return result;
	}
	
	
	public RuleQuery convertAtpcoRecord2GroupByRuleNoCxrTarNo(AtpcoRecord2GroupByRuleNoCxrTarNo record2Group) {

		RuleQuery result = new RuleQuery();
		
		result.setCxr(record2Group.getCarrierCode());
		result.setRuleNo(record2Group.getRuleNo());
		result.setTarNo(record2Group.getRuleTariffNo());
		result.setSrc("A");
		result.setType("Rule");
		
		return result;
	}
}
