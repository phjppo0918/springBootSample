package com.rptp.rptpSpringBoot.repository.impliment;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    // 생성자가 단 한 개인 경우 Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", memberDTO.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        memberDTO.setId(key.longValue());
        return memberDTO;
    }

    @Override
    public Optional<MemberDTO> findById(Long id) {
        List<MemberDTO> result = jdbcTemplate.
                query("select * from member where id = ?", memberDTORowMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<MemberDTO> memberDTORowMapper() {
        return (rs, rowNum) -> {

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setId(rs.getLong("id"));
            memberDTO.setName(rs.getString("name"));
            return memberDTO;
        };
    }

    @Override
    public List<MemberDTO> findAll() {
        return jdbcTemplate.query("select * from member", memberDTORowMapper());
    }
    @Override
    public Optional<MemberDTO> findByName(String name) {
        List<MemberDTO> result = jdbcTemplate.query("select * from member where name = ?", memberDTORowMapper(), name);
        return result.stream().findAny();
    }
}
