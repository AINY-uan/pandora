package org.ainy.pandora.entity.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class Apps implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用id
     */
    private Long id;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用类型
     */
    private String appType;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
}