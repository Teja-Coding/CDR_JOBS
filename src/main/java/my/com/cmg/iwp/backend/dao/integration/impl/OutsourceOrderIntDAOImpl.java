package my.com.cmg.iwp.backend.dao.integration.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import my.com.cmg.iwp.backend.model.integration.OutsourceOrderInt;
import my.com.cmg.iwp.maintenance.dao.impl.BasisNextidDaoImpl;

@Repository
@Primary
public class OutsourceOrderIntDAOImpl extends
		BasisNextidDaoImpl<OutsourceOrderInt> {

}
