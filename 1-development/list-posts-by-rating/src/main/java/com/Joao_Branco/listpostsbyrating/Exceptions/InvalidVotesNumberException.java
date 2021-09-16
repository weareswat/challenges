package com.Joao_Branco.listpostsbyrating.Exceptions;

public class InvalidVotesNumberException extends RuntimeException{

    private int voteNumber;

    public InvalidVotesNumberException(String message, int voteNumber) {

        super(message);
        this.voteNumber = voteNumber;
    }

    public int getVoteNumber() {
        return voteNumber;
    }
}
