package com.hotel.hotelapi.enums;

public enum HotelStatus {

	CONFIRMADO(1), PENDENTE_PAGAMENTO(2), CANCELADO(3);

	public final int status;

	private HotelStatus(int status) {
		this.status = status;
	}

	public static HotelStatus Status(int id) {

		if (id == 1) {
			return CONFIRMADO;
		} else if (id == 2) {
			return PENDENTE_PAGAMENTO;
		} else {
			return CANCELADO;
		}
	}
}
