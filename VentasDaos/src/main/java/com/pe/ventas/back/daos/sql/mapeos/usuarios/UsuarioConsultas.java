package com.pe.ventas.back.daos.sql.mapeos.usuarios;

interface UsuarioConsultas {

    String SELECT_USUARIOS = "<script>SELECT * FROM ventas.Usuarios</script>";
   /* String INSERT_USUARIO = "<script>\n" + "INSERT INTO ejemplo_java_1.Usuarios \n" + "(alias,\n" + "correo,\n"
            + "contrasenya\n" + "<if test=\"acceso != null\">\n" + "    ,acceso\n" + "</if>\n"
            + "<if test=\"modificacion != null\">\n" + "    ,modificacion\n" + "</if>\n" + ") \n"
            + "VALUES(#{alias,jdbcType=VARCHAR},\n" + "#{correo,jdbcType=VARCHAR},\n"
            + "PASSWORD(#{contrasenya,jdbcType=VARCHAR})\n" + "<if test=\"acceso != null\">\n"
            + "    ,#{acceso,jdbcType=TIMESTAMP}\n" + "</if>\n" + "<if test=\"modificacion != null\">\n"
            + "    ,#{modificacion,jdbcType=TIMESTAMP}\n" + "</if>\n" + ")\n" + "</script>";
*/
    /*
    String UPDATE_USUARIO = "<script>\n" + "  UPDATE ejemplo_java_1.Usuarios\n" + "    <set>\n"
            + "      <if test=\"alias != null\">alias=#{alias,jdbcType=VARCHAR},</if>\n"
            + "      <if test=\"correo != null\">correo=#{correo,jdbcType=VARCHAR},</if>\n"
            + "      <if test=\"contrasenya != null\">contrasenya=#{contrasenya,jdbcType=VARCHAR},</if>\n"
            + "      <if test=\"acceso != null\">acceso=#{acceso,jdbcType=TIMESTAMP},</if>\n"
            + "      <if test=\"modificacion != null\">modificacion=#{modificacion,jdbcType=TIMESTAMP}</if>\n"
            + "    </set>\n" + "  where id=#{id,jdbcType=INTEGER}\n" + "</script>";
*/
   /* String DELETE_USUARIO = "<script>\n" + "DELETE FROM ejemplo_java_1.Usuarios  \n" + "<where>\n"
            + "    <if test=\"id != null\">id=#{id,jdbcType=INTEGER}</if>\n"
            + "    <if test=\"alias != null\"> AND alias=#{alias,jdbcType=VARCHAR}</if>\n"
            + "    <if test=\"correo != null\"> AND correo=#{correo,jdbcType=VARCHAR}</if>\n" + "</where>\n"
            + "</script>";*/

}
