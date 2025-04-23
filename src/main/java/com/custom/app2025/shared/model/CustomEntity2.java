package com.custom.app2025.shared.model;

import java.time.LocalDateTime;

import com.custom.app2025.shared.constant.CmmnConstant;
import com.custom.app2025.shared.utils.LocalDateTimeUtils;
import com.custom.app2025.shared.utils.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

/**
 * <pre>
 * 클래스명: CustomEntity2
 * 설명: 공통처리를 위해 상속한다
 * </pre>
 */
@MappedSuperclass
public abstract class CustomEntity2 {
	
	@Transient
	private String sysActor;
	
	@Column(name = "sysCreator", nullable = false, updatable = false)
	private String sysCreator;
	
	@Column(name = "sysModifier", nullable = false)
	private String sysModifier;
	
	@Column(name = "sysCreatedAt", nullable = false, updatable = false)
    private LocalDateTime sysCreatedAt;

    @Column(name = "sysModifiedAt", nullable = false)
    private LocalDateTime sysModifiedAt;
    

    
    @PrePersist
    protected void onCreate() {
    	if (StringUtils.isNVL(this.sysActor)) {
    		this.sysCreator = CmmnConstant.SYSTEM;
    		this.sysModifier = CmmnConstant.SYSTEM;
    	} else {
    		this.sysCreator = this.sysActor;
    		this.sysModifier = this.sysActor;
    	}
        this.sysCreatedAt = LocalDateTime.now();
        this.sysModifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	if (StringUtils.isNVL(this.sysActor)) {
    		this.sysModifier = CmmnConstant.SYSTEM;
    	} else {
    		this.sysModifier = this.sysActor;
		}
        this.sysModifiedAt = LocalDateTime.now();
    }
    
	public void setSysActor(String sysActor) {
		this.sysActor = sysActor;
	}

    
	@Override
	public String toString() {
		return CustomMap.objectToString(this, new String[] {}, true);
	}
	
	public CustomMap toCustomMap() {
		return CustomMap.objectToCustomMap(this, new String[] {}, true);
	}
	
	public String getSysCreator() {
		return sysCreator;
	}

	public String getSysModifier() {
		return sysModifier;
	}

	public LocalDateTime getSysCreatedAt() {
		return sysCreatedAt;
	}

	public LocalDateTime getSysModifiedAt() {
		return sysModifiedAt;
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
