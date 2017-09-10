package by.vasilevsky.leasing.service.payments;

import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;

public interface PaymentsScheduleService {
	
	void calculatePayments(PaymentsSchedule paymentsSchedule);
}
