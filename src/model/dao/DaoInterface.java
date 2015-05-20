package model.dao;

import model.entity.Entity;
import util.common.DataNotFoundException;

import java.util.List;

/**
 * Created by hdd on 12/05/15.
 */
public interface DaoInterface {
    public Entity getEntity(int id) throws DataNotFoundException;

    public List<Entity> getAllEntity() throws DataNotFoundException;

    public void addEntity(Entity entity);

    public boolean delEntity(int id);

    public boolean updateEntity(Entity entity);
}
