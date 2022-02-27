package com.react.framework.ext.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author qinjh
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

  /**
   * 返回一个账号所拥有的权限码集合
   *
   * @param loginId
   * @param loginType
   * @return
   */
  @Override
  public List<String> getPermissionList(Object loginId, String loginType) {
    log.info("获取账号 {} 的权限集合", loginId);

    List<String> permissionList = new ArrayList<>();
    // 获取用户Session缓存,没有则创建一个
    SaSession session = StpUtil.getSession(true);
    // 根据Session中缓存来直接读取权限,减缓数据库压力,优化授权时间
    if (session.has("permissionList")) {
      permissionList = (List<String>) session.get("permissionList");
    } else {
      session.set("permissionList", permissionList);
    }
    return permissionList;
  }

  /**
   * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
   *
   * @param loginId
   * @param loginType
   * @return
   */
  @Override
  public List<String> getRoleList(Object loginId, String loginType) {
    log.info("获取账号 {} 的角色集合", loginId);

    List<String> roleList = new ArrayList<>();
    // 获取用户Session缓存,没有则创建一个
    SaSession session = StpUtil.getSession(true);
    // 根据Session中缓存来直接读取角色,减缓数据库压力,优化授权时间
    if (session.has("roleList")) {
      roleList = (List<String>) session.get("roleList");
    } else {
      session.set("roleList", roleList);
    }
    return roleList;
  }
}
