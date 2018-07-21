package com.atibusinessgroup.fmp.domain.dto;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Field;

public class TaxTicketValue {

	@Field("ticket_value_min")
	private Decimal128 ticket_value_min;

	@Field("ticket_value_max")
    private Decimal128 ticket_value_max;

	@Field("ticket_value_app")
    private String ticket_value_app;

	@Field("ticket_value_dec")
    private int ticket_value_dec;

	@Field("ticket_value_cur")
    private String ticket_value_cur;

	@Field("ticket_value_cur_of_sale")
    private String ticket_value_cur_of_sale;

	public Decimal128 getTicket_value_min() {
		return ticket_value_min;
	}

	public void setTicket_value_min(Decimal128 ticket_value_min) {
		this.ticket_value_min = ticket_value_min;
	}

	public Decimal128 getTicket_value_max() {
		return ticket_value_max;
	}

	public void setTicket_value_max(Decimal128 ticket_value_max) {
		this.ticket_value_max = ticket_value_max;
	}

	public String getTicket_value_app() {
		return ticket_value_app;
	}

	public void setTicket_value_app(String ticket_value_app) {
		this.ticket_value_app = ticket_value_app;
	}

	public int getTicket_value_dec() {
		return ticket_value_dec;
	}

	public void setTicket_value_dec(int ticket_value_dec) {
		this.ticket_value_dec = ticket_value_dec;
	}

	public String getTicket_value_cur() {
		return ticket_value_cur;
	}

	public void setTicket_value_cur(String ticket_value_cur) {
		this.ticket_value_cur = ticket_value_cur;
	}

	public String getTicket_value_cur_of_sale() {
		return ticket_value_cur_of_sale;
	}

	public void setTicket_value_cur_of_sale(String ticket_value_cur_of_sale) {
		this.ticket_value_cur_of_sale = ticket_value_cur_of_sale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticket_value_app == null) ? 0 : ticket_value_app.hashCode());
		result = prime * result + ((ticket_value_cur == null) ? 0 : ticket_value_cur.hashCode());
		result = prime * result + ((ticket_value_cur_of_sale == null) ? 0 : ticket_value_cur_of_sale.hashCode());
		result = prime * result + ticket_value_dec;
		result = prime * result + ((ticket_value_max == null) ? 0 : ticket_value_max.hashCode());
		result = prime * result + ((ticket_value_min == null) ? 0 : ticket_value_min.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TaxTicketValue other = (TaxTicketValue) obj;
		if (ticket_value_app == null) {
			if (other.ticket_value_app != null) {
				return false;
			}
		} else if (!ticket_value_app.equals(other.ticket_value_app)) {
			return false;
		}
		if (ticket_value_cur == null) {
			if (other.ticket_value_cur != null) {
				return false;
			}
		} else if (!ticket_value_cur.equals(other.ticket_value_cur)) {
			return false;
		}
		if (ticket_value_cur_of_sale == null) {
			if (other.ticket_value_cur_of_sale != null) {
				return false;
			}
		} else if (!ticket_value_cur_of_sale.equals(other.ticket_value_cur_of_sale)) {
			return false;
		}
		if (ticket_value_dec != other.ticket_value_dec) {
			return false;
		}
		if (ticket_value_max == null) {
			if (other.ticket_value_max != null) {
				return false;
			}
		} else if (!ticket_value_max.equals(other.ticket_value_max)) {
			return false;
		}
		if (ticket_value_min == null) {
			if (other.ticket_value_min != null) {
				return false;
			}
		} else if (!ticket_value_min.equals(other.ticket_value_min)) {
			return false;
		}
		return true;
	}
}
