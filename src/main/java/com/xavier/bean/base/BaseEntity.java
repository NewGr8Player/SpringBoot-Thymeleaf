package com.xavier.bean.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * 提取公共字段,作为基类
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	protected String id;

	@Override
	public String toString() {
		return this.getClass().getName() + ":" + JSON.toJSONString(this);
	}
}
