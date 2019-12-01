package com.hotel.hotelapi.enums;

public enum HotelStatus {
	
	CONFIRMADO(1),
	PENDENTE_PAGAMENTO(2),
	CANCELADO(3);
	
	public final int status;
	 
    private HotelStatus(int status) {
        this.status = status;
    }
}
