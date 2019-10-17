package cn.sample.notes.domain;

import java.util.Date;

public class NinGxNotes {
    /**
     * notes_id
     */
    private Long notesId;

    /**
     * course_id
     */
    private Long courseId;

    /**
     * 节Id
     */
    private Long nodeId;

    /**
     * 用户账号
     */
    private String userCode;

    /**
     * 做笔记者姓名
     */
    private String userName;

    /**
     * 标签
     */
    private String notesTypeName;

    /**
     * 机构编码
     */
    private String institutionCode;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新者
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 逻辑删除
     */
    private Integer del;

    /**
     * 引用笔记id
     */
    private Long fromId;

    /**
     * 课件笔记截图，默认在笔记一行显示
     */
    private String noteImg;

    /**
     * 笔记内容
     */
    private String notesContent;

    private static final long serialVersionUID = 1L;

    /**
     * 获取notes_id
     *
     * @return notes_id - notes_id
     */
    public Long getNotesId() {
        return notesId;
    }

    /**
     * 设置notes_id
     *
     * @param notesId notes_id
     */
    public void setNotesId(Long notesId) {
        this.notesId = notesId;
    }

    /**
     * 获取course_id
     *
     * @return course_id - course_id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * 设置course_id
     *
     * @param courseId course_id
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取节Id
     *
     * @return node_id - 节Id
     */
    public Long getNodeId() {
        return nodeId;
    }

    /**
     * 设置节Id
     *
     * @param nodeId 节Id
     */
    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 获取用户账号
     *
     * @return user_code - 用户账号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户账号
     *
     * @param userCode 用户账号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取做笔记者姓名
     *
     * @return user_name - 做笔记者姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置做笔记者姓名
     *
     * @param userName 做笔记者姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取标签
     *
     * @return notes_type_name - 标签
     */
    public String getNotesTypeName() {
        return notesTypeName;
    }

    /**
     * 设置标签
     *
     * @param notesTypeName 标签
     */
    public void setNotesTypeName(String notesTypeName) {
        this.notesTypeName = notesTypeName;
    }

    /**
     * 获取机构编码
     *
     * @return institution_code - 机构编码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置机构编码
     *
     * @param institutionCode 机构编码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后更新者
     *
     * @return modify_user_id - 最后更新者
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置最后更新者
     *
     * @param modifyUserId 最后更新者
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取逻辑删除
     *
     * @return del - 逻辑删除
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 设置逻辑删除
     *
     * @param del 逻辑删除
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 获取引用笔记id
     *
     * @return from_id - 引用笔记id
     */
    public Long getFromId() {
        return fromId;
    }

    /**
     * 设置引用笔记id
     *
     * @param fromId 引用笔记id
     */
    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    /**
     * 获取课件笔记截图，默认在笔记一行显示
     *
     * @return note_img - 课件笔记截图，默认在笔记一行显示
     */
    public String getNoteImg() {
        return noteImg;
    }

    /**
     * 设置课件笔记截图，默认在笔记一行显示
     *
     * @param noteImg 课件笔记截图，默认在笔记一行显示
     */
    public void setNoteImg(String noteImg) {
        this.noteImg = noteImg;
    }

    /**
     * 获取笔记内容
     *
     * @return notes_content - 笔记内容
     */
    public String getNotesContent() {
        return notesContent;
    }

    /**
     * 设置笔记内容
     *
     * @param notesContent 笔记内容
     */
    public void setNotesContent(String notesContent) {
        this.notesContent = notesContent;
    }
}