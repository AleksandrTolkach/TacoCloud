package tacos.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import tacos.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
  
  Page<Taco> findAllBy(PageRequest page);
}
