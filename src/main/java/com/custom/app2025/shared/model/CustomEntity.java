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
import jakarta.persistence.Transient;
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
	
	@Transient
	private String sysActor;
	
	@Column(name = "시스템생성자", nullable = false, updatable = false)
	private String sysCreator;
	
	@Column(name = "시스템수정자", nullable = false)
	private String sysModifier;
	
	@Column(name = "시스템생성일", nullable = false, updatable = false)
    private LocalDateTime sysCreatedAt;

    @Column(name = "시스템수정일", nullable = false)
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
    
    /**
     * <pre>
     * 메서드명: setSysActor
     * 설명: sysCreator 및 sysModifier 적용값
     * </pre>
     * @param sysActor
     */
	public void setSysActor(String sysActor) {
		this.sysActor = sysActor;
	}

    
	@Override
	public String toString() {
		return toCustomMap().toString().replace("CustomMap", this.getClass().getSimpleName());
	}
	
	public CustomMap toCustomMap() {
		return new CustomMap(MapUtils.objectToMap2(this));
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
