package com.relayd.ejb;

import java.util.UUID;

import com.relayd.Member;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   04.06.2017
 *
 */
public interface MemberGateway {

	Member get(UUID uuid);

	void set(Member member);
}