package br.com.caelum.jdbc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.exception.DaoException;

public interface IGenericDao<T> {
	
	public T save(T entity);
	public T update(T entity);
	public List<T> getAll();
	public T getById(Long id);
	public void delete(Long id);	

	public default Map<String, String> getFieldMap(Object entity) {
		
		Map<String, String> fields = new HashMap<String,String>();
		
		for(Method metodo : entity.getClass().getMethods())
		{
			if(metodo.getName().contains("get") || metodo.getName().contains("is"))
			{
				String nome = metodo.getName().replaceAll("get", "");
				nome = nome.replaceAll("is", "");

				nome = nome.replace(String.valueOf(nome.charAt(0)), String.valueOf(nome.charAt(0)).toLowerCase());

				if(!nome.equals("class"))
				{
					fields.put(nome, metodo.getReturnType().getSimpleName());
				}
			}

		}
		
		return fields;
	}
	
	public default List<Field> getFieldsNotNull(Object entity){
		
		List<Field> fieldList = Arrays.asList(entity.getClass().getDeclaredFields());
		
		List<Field> fieldsNotNull = new ArrayList<Field>();
		
		for(Field field : fieldList)
		{
			field.setAccessible(true);

			Object value = null;
			try {
				value = field.get(entity);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new DaoException("Erro ao recuperar valor de campo na inserção - "+entity.getClass().getSimpleName(),e);
			}
			
			if(value!=null)
			{
				fieldsNotNull.add(field);
			}
		}
		
		return fieldsNotNull;
	}
	
	public default String generateSql(Object entity) {
		
		String sql = "insert into ".concat(entity.getClass().getSimpleName().toLowerCase()).concat(" (");

		String fields = "";
		String values = "";
		
		for(Field field : getFieldsNotNull(entity))
		{			
			String fieldName = field.getName();			
			fieldName = String.valueOf(fieldName.charAt(0)).toLowerCase().concat(fieldName.substring(1, fieldName.length()));
			
			if(fields.isEmpty())
			{				
				fields = fields.concat(fieldName);
				values = values.concat("?");
			}
			else
			{
				fields = fields.concat(","+fieldName);
				values = values.concat(",?");
			}	
		}

		sql = sql.concat(fields).concat(") ");

		sql = sql.concat("values(").concat(values).concat(")");
		
		return sql;
	}
	
	public default PreparedStatement getInsertPreparedStatement(Connection connection, Object entity)
	{
		
		Map<String,String> lista = getFieldMap(entity);
		
		List<Field> fieldsNotNull = getFieldsNotNull(entity);
		
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(generateSql(entity));
		} catch (SQLException e) {
			throw new DaoException("Erro na geração do SQL na inserção - "+entity.getClass().getName(),e);
		}

		int contador = 1;

		for(Field field : fieldsNotNull)
		{
			field.setAccessible(true);

			Object value;
			try {
				value = field.get(entity);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new DaoException("Erro ao recuperar valor de campo na inserção - "+entity.getClass().getName(),e);
			}

			if(!field.getName().equalsIgnoreCase("id"))
			{

				switch(lista.get(field.getName())) {

				case "Integer" : 
				case "int" :
				{
					try 
					{
						stmt.setInt(contador, Integer.parseInt((String) value));					
					} 
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "Long" :
				case "long":
				{
					try 
					{
						stmt.setLong(contador, Long.parseLong(String.valueOf(value)));						
					} 
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "Float" :
				case "float":
				{
					try 
					{
						stmt.setFloat(contador, Float.parseFloat(String.valueOf(value)));		
					}
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "Double" :
				case "double":
				{
					try 
					{
						stmt.setDouble(contador, Double.parseDouble(String.valueOf(value)));	
					}
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "Boolean" :
				case "boolean":
				{
					try 
					{	
						stmt.setBoolean(contador, Boolean.parseBoolean(String.valueOf(value)));						
					} 
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "String" :
				{					
					try 
					{
						stmt.setString(contador, String.valueOf(value));
					} 
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "Calendar" :
				{					
					try 
					{
							stmt.setDate(contador, new Date(((Calendar) value).getTimeInMillis()));
					} 
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}
				case "Date" :
				{
					try 
					{	
						stmt.setDate(contador, Date.valueOf(String.valueOf(value)));
					} 
					catch (NumberFormatException | SQLException e) 
					{
						throw new DaoException("Erro na definição de valores na inserção - "+entity.getClass().getName(),e);
					}
					contador++;
					break;
				}

				}

			}
		}
		
		return stmt;
	}
	
}
