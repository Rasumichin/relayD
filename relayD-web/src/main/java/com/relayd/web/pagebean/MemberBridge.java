package com.relayd.web.pagebean;

import java.util.UUID;

import com.relayd.Member;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.06.2017
 *
 */
public interface MemberBridge {
	Member get(UUID uuid);

	void set(Member member);
}