package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByRuleNoCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.RuleQuery;

@Service
public class RuleQueryMapper {
	
	
	public RuleQuery convertAtpcoRecord2GroupByRuleNoCxrTarNo(AtpcoRecord2GroupByRuleNoCxrTarNo record2Group) {

		RuleQuery result = new RuleQuery();
		
		result.setCxr(record2Group.getCxr());
		result.setRuleNo(record2Group.getRuleNo());
		result.setTarNo(record2Group.getTarNo());
		result.setSrc("A");
		result.setType(record2Group.getTarCd());
		
		return result;
	}
}
