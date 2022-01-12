package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    @Query("Select u FROM User u WHERE u.email=?1")
    Optional<User>findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name like ?1%")
    List<User>findAndSort8(String name, Sort sort);

    List<User>findByName(String name);

    Optional<User>findByEmailAndName(String email,String name);

    List<User>findByNameLike(String name);

    //@Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id,u.name,u.birthDate)" +
      ///      "FROM User u" +

         //   "WHERE u.birthDate=:parametroFecha" +
           // "and u.email=:parametroEmail")
    //Optional<UserDto>getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date, @Param("parametroEmail") String email);


}