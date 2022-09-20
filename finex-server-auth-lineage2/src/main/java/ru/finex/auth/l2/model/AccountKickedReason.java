package ru.finex.auth.l2.model;

import lombok.Getter;

/**
 * @author l2j-server
 */
public enum AccountKickedReason {
	REASON_DATA_STEALER(0x01),
	REASON_GENERIC_VIOLATION(0x08),
	REASON_7_DAYS_SUSPENDED(0x10),
	REASON_PERMANENTLY_BANNED(0x20);

	@Getter
	private final int messageId;
	
	AccountKickedReason(int messageId) {
		this.messageId = messageId;
	}
}