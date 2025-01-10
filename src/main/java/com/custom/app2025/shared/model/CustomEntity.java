package com.custom.app2025.shared.model;

import java.time.LocalDateTime;

import com.custom.app2025.shared.constant.CmmnConstant;
import com.custom.app2025.shared.utils.LocalDateTimeUtils;
import com.custom.app2025.shared.utils.MapUtils;
import com.custom.app2025.shared.utils.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

/**
 * <pre>
 * 클래스명: CustomEntity
 * 설명: 공통처리를 위해 상속한다
 * </pre>
 */
@MappedSuperclass
@Getter
public abstract class CustomEntity {
	
	@Column(name = "시스템생성자", nullable = true, updatable = false)
	private String sysCreator;
	
	@Column(name = "시스템수정자", nullable = true)
	private String sysModifier;
	
	@Column(name = "시스템생성일", nullable = false, updatable = false)
    private LocalDateTime sysCreatedAt;

    @Column(name = "시스템수정일", nullable = false)
    private LocalDateTime sysModifiedAt;

    
    @PrePersist
    protected void onCreate() {
    	if (StringUtils.isNVL(sysCreator)) {
    		this.sysCreator = CmmnConstant.SYSTEM;
    		this.sysModifier = CmmnConstant.SYSTEM;
    	} else {
    		if (StringUtils.isNVL(sysModifier)) {
    			this.sysModifier = this.sysCreator;
    		}
    	}
        this.sysCreatedAt = LocalDateTime.now();
        this.sysModifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	if (StringUtils.isNVL(sysModifier)) {
    		this.sysModifier = CmmnConstant.SYSTEM;
    	}
        this.sysModifiedAt = LocalDateTime.now();
    }
    
	public void setSysCreator(String sysCreator) {
		this.sysCreator = sysCreator;
	}

	public void setSysModifier(String sysModifier) {
		this.sysModifier = sysModifier;
	}
    
    
	@Override
	public String toString() {
		return toCustomMap().toString().replace("CustomMap", this.getClass().getSimpleName());
	}
	
	public CustomMap toCustomMap() {
		return new CustomMap(MapUtils.objectToMap(this));
	}
	
	public String getSimpleSysCreatedAt() {
		if (this.sysCreatedAt != null) {
			return LocalDateTimeUtils.format(this.sysCreatedAt, "yyyy-MM-dd HH:mm:ss");
		} else {
			return "";
		}
	}
	
	public String getSimpleSysModifiedAt() {
		if (this.sysModifiedAt != null) {
			return LocalDateTimeUtils.format(this.sysModifiedAt, "yyyy-MM-dd HH:mm:ss");
		} else {
			return "";
		}
	}


}
