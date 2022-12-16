package com.example.chap1;

import java.util.Arrays;
import java.util.List;

public class Dispatch2 {
    interface Post {
        void postOn(SNS sns);
    }

    static class Text implements Post {
        @Override
        public void postOn(SNS sns) {
            sns.post(this);
        }
    }

    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) {
            sns.post(this);
        }
    }

    interface SNS {
        void post(Text post);
        void post(Picture post);
    }
    static class Facebook implements SNS {
        @Override
        public void post(Text post) { System.out.println("text-facebook"); }

        @Override
        public void post(Picture post) { System.out.println("picture-facebook"); }
    }
    static class Twitter implements SNS {
        @Override
        public void post(Text post) { System.out.println("text-twitter"); }

        @Override
        public void post(Picture post) { System.out.println("picture-twitter"); }
    }

    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());

        posts.forEach(p -> sns.forEach(p::postOn));
    }

    // 파라미터는 다이다닉 디스패치의 조건이 되지 않음.
    // 1:10:04
}
