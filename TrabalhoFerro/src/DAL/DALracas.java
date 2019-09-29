/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;


import banco.Banco;
import entidades.Racas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class DALracas
{
    public boolean salvar(Racas r)
    {
        String sql = "insert into raca values (default,'#1') ";
        sql = sql.replace("#1",r.getNome());        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar(Racas r)
    {
        String sql = " update raca set rac_nome = '#1' where rac_cod = '#2' ";
        sql = sql.replace("#1", r.getNome());
        sql = sql.replace("#2",""+r.getCod());
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(Racas r)
    {
        return Banco.getCon().manipular("delete from raca where rac_cod = "+r.getCod());
    }
    public List <Racas> get(String filtro)
    {
        List <Racas> raca= new ArrayList();
        String sql = "select * from raca";
        if(!filtro.isEmpty())
            sql += "where "+filtro;
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            if(rs.next())
                raca.add(new Racas(rs.getInt("rac_cod"),rs.getString("rac_nome")));
        }
        catch(SQLException ex)
        {
            
        }
        return raca;
    }
    public Racas getUm(int cod)
    {   
        Racas raca=null;
        String sql="select * from raca where rac_cod = "+cod;
        ResultSet rs=Banco.getCon().consultar(sql);
        try{
           if(rs.next())
              raca = new Racas(cod,rs.getString("rac_nome"));
        }
        catch(Exception e){}
        return raca;
    }
    public List <Racas> get()
    {
        List <Racas> raca= new ArrayList();
        String sql = "select * from raca";
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            if(rs.next())
                raca.add(new Racas(rs.getInt("rac_cod"),rs.getString("rac_nome")));
        }
        catch(SQLException ex)
        {
            
        }
        return raca;
    }
}