package pl.pwr.zpi.cinemapro.domain.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public List<Group> findByMovieId(UUID id) {
        return groupRepository.findByMovieId(id);
    }
    
    public List<Group> findByClientId(UUID id) {
        return groupRepository.findByMovieId(id);
    }

    public Group findById(UUID id){
        return groupRepository.findById(id);
    }


    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Group group) {
        groupRepository.delete(group);
    }

}
