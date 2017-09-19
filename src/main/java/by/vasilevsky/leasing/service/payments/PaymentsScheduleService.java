package by.vasilevsky.leasing.service.payments;

import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;

public interface PaymentsScheduleService {
	float VAT_RATE = 0.2f;
	
	void countPayments(PaymentsSchedule paymentsSchedule);
}
