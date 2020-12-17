package org.dasd.persistence;

import org.dasd.domain.Profile;
import org.springframework.data.repository.CrudRepository;

//Member 객체를 통해서 Profile 객체를 처리할 수 없으므로 따로 만들었다.
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
