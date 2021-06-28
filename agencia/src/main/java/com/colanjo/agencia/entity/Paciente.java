package com.colanjo.agencia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Paciente {

     @Id
     @GeneratedValue
     private int id;
     private String nome;
     private String  cpf;
     private String  rg;
     private String  data_nasc;
     private String  sexo;
     private String  mae;
     private String  pai;
     private String  email;
     private String  cep;
     private String  endereco;
     private int     numero;
     private String  bairro;
     private String  cidade;
     private String  estado;
     private String  telefone_fixo;
     private String  celular;
     private float   altura;
     private float   peso;
     private String  tipo_sanguineo;
     private int idade;
     private double IMC;

    public Paciente(){}

    public Paciente(int id, String nome, String cpf, String rg, String data_nasc, String sexo, String mae, String pai, String email, String cep, String endereco, int numero, String bairro, String cidade, String estado, String telefone_fixo, String celular, float altura, float peso, String tipo_sanguineo) {
        this.id =  id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.mae = mae;
        this.pai = pai;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.altura = altura;
        this.peso = peso;
        this.tipo_sanguineo = tipo_sanguineo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone_fixo() {
        return telefone_fixo;
    }

    public void setTelefone_fixo(String telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTipo_sanguineo() {
        return tipo_sanguineo;
    }

    public void setTipo_sanguineo(String tipo_sanguineo) {
        this.tipo_sanguineo = tipo_sanguineo;
    }

    public int getIdade() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(getData_nasc(), format);
        LocalDate localDate = LocalDate.now();
        idade = localDate.getYear() - dataNasc.getYear();
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getIMC() {
        IMC = getPeso() / (getAltura() * getAltura());
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return id == paciente.id && Objects.equals(cpf, paciente.cpf) && Objects.equals(rg, paciente.rg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, rg);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", data_nasc='" + data_nasc + '\'' +
                ", sexo='" + sexo + '\'' +
                ", mae='" + mae + '\'' +
                ", pai='" + pai + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefone_fixo='" + telefone_fixo + '\'' +
                ", celular='" + celular + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", tipo_sanguineo='" + tipo_sanguineo + '\'' +
                '}';
    }
}
