package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

	// 본 메모리 저장소에는 HashMap을 사용했지만, 실무에서는 동시성 문제가 생기기에 ConcurrentHashMap(멀티스레드 지원)을 사용해야한다.
	private static Map<Long, Member> store = new HashMap<>();
	
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}

}
