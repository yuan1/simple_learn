package cn.javayuan.system.entity;

import javax.persistence.*;

@Table(name = "sys_user_dept")
public class SysUserDept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sys_user_id")
    private Integer sysUserId;

    @Column(name = "sys_dept_id")
    private Integer sysDeptId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sys_user_id
     */
    public Integer getSysUserId() {
        return sysUserId;
    }

    /**
     * @param sysUserId
     */
    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * @return sys_dept_id
     */
    public Integer getSysDeptId() {
        return sysDeptId;
    }

    /**
     * @param sysDeptId
     */
    public void setSysDeptId(Integer sysDeptId) {
        this.sysDeptId = sysDeptId;
    }
}