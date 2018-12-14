--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: favorites; Type: TABLE; Schema: public; Owner: rakesh
--

CREATE TABLE favorites (
    userid character varying(50),
    title text
);

--
-- Name: news; Type: TABLE; Schema: public; Owner: rakesh
--

CREATE TABLE news (
    title text,
    description text,
    author character(50),
    security character varying(50),
    userid character varying(50)
);

--
-- Name: users; Type: TABLE; Schema: public; Owner: rakesh
--

CREATE TABLE users (
    first_name character(50),
    last_name character(50),
    role character(25),
    user_name character varying(50),
    password character varying(50)
);


--
-- Data for Name: favorites; Type: TABLE DATA; Schema: public; Owner: rakesh
--

COPY favorites (userid, title) FROM stdin;
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: rakesh
--

COPY news (title, description, author, security, userid) FROM stdin;
WH spokesman: John Kelly hasn't offered to resign	The White House is denying chief of staff John Kelly has offered to resign. "He has not offered to resign," spokesman Hogan Gidley told CNN. The White House statement comes as Kelly and other top administration officials are facing questions about why they did not act to remove deputy chief of staff Rob Porter from his position after they found out about allegations that Porter struck his two-ex-wives while they were married. Senior aides knew for months about the allegations levied against Porter, even as his stock in the West Wing continued to rise, multiple sources have told CNN. Porter denied the allegations but resigned on Wednesday. Even after the allegations surfaced publicly, sources told CNN that Kelly did not urge Porter to resign or seek to force him out. Instead, the White House released a statement from Kelly praising Porter's character. It wasn't until the uproar over the allegations grew and more details surfaced that Porter resigned and Kelly put out a second statement expressing concern about the allegations. On Thursday, deputy press secretary Raj Shah issued a rare admission that the White House could have handled the Porter situation better. "We all could have done better over the few hours or last few days in dealing with this situation," Shah told reporters Thursday. "But you know this was a Rob Porter that I and many others have dealt with, that Sarah (Sanders) had dealt with, that other officials, including the chief of staff had dealt with. And the emerging reports were not reflective of the individual who we had come to know."	Jeremy                                            	public	amohan12
Trump on Rob Porter: 'We wish him well ... He did a good job'	President Donald Trump on Friday called the departure of his former staff secretary Rob Porter following allegations of domestic abuse "very sad" and said he wishes Porter "well. "The President, in his first public comments since Porter's resignation on Wednesday, did not express any sympathy for the women Porter allegedly abused -- instead pointing to Porter's claim that "he's innocent." "I found out about it recently and I was surprised by it," Trump said. "We certainly wish him well. It's obviously a very tough time for him. He did a very good job while he was in the White House." "We hope that he will have a wonderful career, "Trump said "it was very sad when we heard about it." "He says he's innocent, and I think you have to remember that," Trump said. "He said very strongly yesterday that he's innocent, but you'll have to talk to him about that. "John Kelly facing questions Trump's comments come as chief of staff John Kelly and other top White House officials are facing questions about why they did not act to remove Porter from his position after they found out about the allegations of domestic abuse. Senior aides knew for months about the allegations levied against Porter by his ex-wives, even as Porter's stock in the West Wing continued to rise, multiple sources have told CNN. Porter denied the allegations but resigned on Wednesday. Even after the allegations surfaced publicly, sources told CNN that Kelly did not urge Porter to resign or seek to force him out. Instead, the White House released a statement from Kelly praising Porter's character. It wasn't until the uproar over the allegations grew and more details surfaced that Porter resigned and Kelly put out a second statement expressing concern about the allegations. White House deputy press secretary Raj Shah acknowledged on Thursday the White House "could have done better" in its initial response to the allegations, a concession Trump was privately miffed about, CNN has learned. The allegations against Porter and the problematic internal response to the allegations -- which former senior staffers had known about for months -- has led to tensions inside the West Wing, with Kelly's credibility in particular coming under fire. Trump has also grown upset with his White House communications director Hope Hicks, who despite being in a romantic relationship with Porter helped craft the White House's initial pushback to the allegations, a source familiar said.	Jeremy                                            	public	amohan11
Trump tweets argument that he's been 'victimized' by Obama administration	President Donald Trump is promoting a conservative argument that he's been "victimized" by the Obama administration through its allegedly flawed application for a surveillance warrant on a former campaign foreign policy adviser.\r\n"'My view is that not only has Trump been vindicated in the last several weeks about the mishandling of the Dossier and the lies about the ClintonDNC Dossier, it shows that he's been victimized. He's been victimized by the Obama Administration who were using all sorts of...........agencies, not just the FBI & DOJ, now the State Department to dig up dirt on him in the days leading up to the Election,'" Trump said Saturday in a pair of tweets, quoting from a Fox News interview given by Tom Fitton, the president of the conservative watchdog group Judicial Watch. \r\n\r\nThe allegations stem from the memo released last week by Republicans on the House Intelligence Committee, along with new accusations that the Obama State Department fed ex-British intelligence agent Christopher Steele information from Hillary Clinton associates, which he then used to reinforce the disputed dossier on Trump and Russia that he provided to the FBI.\r\nThe Republican memo, spearheaded by House Intelligence Committee Chairman Devin Nunes, alleges the FBI abused its surveillance authority by not revealing that the Steele dossier, which was used as part of a FISA warrant to monitor former Trump campaign foreign policy adviser Carter Page, was funded partly by Clinton's campaign.\r\n\r\nThe Nunes memo accuses the Russia probe of being infused with anti-Trump bias under the Obama administration and supported by the dossier.The memo alleges that Steele harbored anti-Trump financial and ideological motivations that were not included in the FISA application, and that senior Justice Department officials knew about Steele's anti-Trump bias.	Rakesh                                            	private	amohan11
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: rakesh
--

COPY users (first_name, last_name, role, user_name, password) FROM stdin;
Ajay                                              	Mohan                                             	subscriber               	amohan11	password
Ben                                               	Mohan                                             	reporter                 	bmohan11	password
Rocky                                             	Balboa                                            	reporter                 	amohan12	password
\.


--
-- Name: favorites; Type: ACL; Schema: public; Owner: rakesh
--

GRANT ALL ON TABLE favorites TO test;


--
-- Name: news; Type: ACL; Schema: public; Owner: rakesh
--

GRANT ALL ON TABLE news TO test;


--
-- Name: users; Type: ACL; Schema: public; Owner: rakesh
--

GRANT ALL ON TABLE users TO test;


--
-- PostgreSQL database dump complete
--

