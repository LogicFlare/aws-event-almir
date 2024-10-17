package api.lg.aws.repositories;

import api.lg.aws.domain.Coupom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoupomRepository extends JpaRepository<Coupom, UUID> {
}
