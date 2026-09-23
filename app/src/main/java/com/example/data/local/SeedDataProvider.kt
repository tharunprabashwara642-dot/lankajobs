package com.example.data.local

import com.example.data.local.entity.JobEntity
import com.example.data.local.entity.UserProfileEntity

object SeedDataProvider {

    fun getDefaultProfile(): UserProfileEntity {
        return UserProfileEntity(
            id = 1,
            fullName = "Kasun Perera",
            headline = "Senior Android Developer | Kotlin Specialist",
            email = "kasun.perera@example.com",
            phone = "+94 77 123 4567",
            preferredLocation = "Colombo & Remote",
            targetRole = "Mobile Engineering",
            preferredCategories = "IT & Software, Engineering",
            experienceLevel = "Mid-Senior Level",
            bio = "Mobile Application Engineer specializing in modern Android development, Jetpack Compose, and clean architecture based in Colombo, Sri Lanka.",
            resumeFileName = "Kasun_Perera_CV_2026.pdf",
            dailyAlertsEnabled = true
        )
    }

    fun getInitialJobs(): List<JobEntity> {
        val now = System.currentTimeMillis()
        val day = 86400000L

        return listOf(
            JobEntity(
                id = "lk-job-001",
                title = "Senior Android Engineer (Jetpack Compose)",
                companyName = "Ceylon Cloud Solutions (Demo)",
                companyDescription = "Ceylon Cloud Solutions is a premier digital technology consultancy based in Colombo, developing high-impact fintech and consumer applications across South Asia.",
                location = "Colombo 03",
                category = "IT & Software",
                employmentType = "Full-time",
                experienceLevel = "Senior Level",
                salaryMin = 350000.0,
                salaryMax = 500000.0,
                salaryCurrency = "LKR",
                description = "We are seeking a seasoned Senior Android Engineer to lead the architecture and development of our next-generation mobile banking experience. You will collaborate with cross-functional product squads to build buttery-smooth Compose interfaces with state-of-the-art offline-first capabilities.",
                responsibilities = """
                    • Architect modern Android mobile applications using Kotlin, Jetpack Compose, and Coroutines
                    • Implement robust offline data synchronization with Room and background worker tasks
                    • Conduct comprehensive code reviews, mentor junior and mid-level Android engineers
                    • Optimize frame rendering, startup latency, and memory footprint across mid-range Android devices
                    • Collaborate directly with product architects and UI/UX designers on design systems
                """.trimIndent(),
                requirements = """
                    • 5+ years of production Android development experience using Kotlin
                    • Deep understanding of Jetpack Compose, MVI/MVVM architectures, and reactive StateFlow
                    • Proven experience with Room Database, modular Gradle setups, and REST/GraphQL APIs
                    • Strong grasp of Material 3 guidelines and Android accessibility best practices
                    • Bachelor's degree in Computer Science, Software Engineering, or equivalent practical experience
                """.trimIndent(),
                benefits = """
                    • Competitive salary pegged to USD with quarterly inflation adjustments
                    • Comprehensive family medical and hospitalization insurance coverage
                    • Flexible hybrid work schedule (2 days in Colombo office, 3 days remote)
                    • Annual professional development & tech certification allowance
                    • Modern MacBook Pro M3 hardware setup provided
                """.trimIndent(),
                postedDate = "Today",
                closingDate = "2026-10-30",
                applyUrl = "https://example.com/apply/ceylon-cloud-android",
                sourceName = "Direct Employer Portal",
                companyWebsite = "https://example.com/ceylon-cloud",
                isFeatured = true,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (1 * day)
            ),
            JobEntity(
                id = "lk-job-002",
                title = "Full Stack Web Developer (Node.js & React)",
                companyName = "Lanka FinEdge Capital (Demo)",
                companyDescription = "Lanka FinEdge is a forward-thinking fintech innovator engineering automated equity analytics and micro-investment tools for Sri Lankan and overseas investors.",
                location = "Colombo 02",
                category = "IT & Software",
                employmentType = "Full-time",
                experienceLevel = "Mid-Senior Level",
                salaryMin = 260000.0,
                salaryMax = 380000.0,
                salaryCurrency = "LKR",
                description = "Join our core engineering group in Colombo to build real-time financial dashboards, transaction ledgers, and secure customer-facing web platforms.",
                responsibilities = """
                    • Build resilient RESTful and WebSocket microservices using Node.js and TypeScript
                    • Develop performant web frontend interfaces using React 18, Tailwind CSS, and Next.js
                    • Integrate secure payment gateways and two-factor authentication systems
                    • Write clean unit and integration tests using Jest and Playwright
                """.trimIndent(),
                requirements = """
                    • 3+ years experience with modern JavaScript/TypeScript in production
                    • Strong proficiency in React, Node.js, PostgreSQL, and Redis caching
                    • Understanding of asynchronous architectures and event-driven patterns
                    • Good verbal and written communication in English
                """.trimIndent(),
                benefits = """
                    • Annual performance bonus and profit share
                    • OPD and inpatient healthcare coverage
                    • Daily catered lunch at the Colombo office
                    • Gym and fitness membership sponsorship
                """.trimIndent(),
                postedDate = "1 day ago",
                closingDate = "2026-11-05",
                applyUrl = "https://example.com/apply/finedge-fullstack",
                sourceName = "FinEdge Careers",
                companyWebsite = "https://example.com/finedge",
                isFeatured = true,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (2 * day)
            ),
            JobEntity(
                id = "lk-job-003",
                title = "Senior Chartered Accountant / Financial Controller",
                companyName = "Apex Lanka Holdings (Demo)",
                companyDescription = "Apex Lanka Holdings is a diversified conglomerate with active business operations spanning renewable energy, hospitality, logistics, and real estate development.",
                location = "Colombo 07",
                category = "Accounting & Finance",
                employmentType = "Full-time",
                experienceLevel = "Lead/Manager",
                salaryMin = 300000.0,
                salaryMax = 450000.0,
                salaryCurrency = "LKR",
                description = "We are hiring an experienced Associate / Fellow member of CA Sri Lanka or ACCA to oversee group financial accounting, statutory compliance, tax planning, and board-level monthly reporting.",
                responsibilities = """
                    • Supervise overall financial reporting across 4 corporate subsidiaries
                    • Ensure strict adherence to Sri Lanka Accounting Standards (SLFRS/LKAS)
                    • Coordinate statutory audits with external audit partners
                    • Oversee corporate income tax filing, RAMIS compliance, and VAT reconciliations
                """.trimIndent(),
                requirements = """
                    • Fully qualified CA Sri Lanka, ACCA, or CIMA qualification
                    • Minimum 6 years of post-qualification corporate finance experience
                    • Advanced mastery of SAP ERP or Oracle NetSuite
                    • Outstanding leadership and dispute resolution capabilities
                """.trimIndent(),
                benefits = """
                    • Executive company vehicle allowance and fuel quota
                    • Comprehensive executive health package
                    • Annual executive performance incentive
                    • Professional membership fee reimbursement
                """.trimIndent(),
                postedDate = "2 days ago",
                closingDate = "2026-10-25",
                applyUrl = "https://example.com/apply/apex-accountant",
                sourceName = "Apex Corporate Careers",
                companyWebsite = "https://example.com/apexlanka",
                isFeatured = true,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (3 * day)
            ),
            JobEntity(
                id = "lk-job-004",
                title = "QA Automation Engineer (Cypress / Playwright)",
                companyName = "Serendib Systems Lab (Demo)",
                companyDescription = "Serendib Systems Lab is a cloud-native software engineering house building enterprise hospitality solutions for clients in Europe, Australia, and Sri Lanka.",
                location = "Remote",
                category = "IT & Software",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 220000.0,
                salaryMax = 320000.0,
                salaryCurrency = "LKR",
                description = "Looking for an energetic Quality Assurance Automation Engineer who can design and execute end-to-end automated test suites for large-scale web and mobile hospitality platforms.",
                responsibilities = """
                    • Develop robust automated regression and smoke test scripts using Playwright and TypeScript
                    • Integrate automated testing stages into GitHub Actions CI/CD pipelines
                    • Work closely with developers to reproduce and diagnose complex race conditions
                    • Maintain bug tracking metrics and test coverage dashboards
                """.trimIndent(),
                requirements = """
                    • 2.5+ years of software testing with at least 1.5 years in automated frameworks
                    • Strong hands-on coding skills in TypeScript, JavaScript, or Python
                    • Experience with REST API testing using Postman or Newman
                    • Familiarity with Docker and continuous integration tooling
                """.trimIndent(),
                benefits = """
                    • 100% remote working flexibility from anywhere in Sri Lanka
                    • Monthly home internet and electricity stipend
                    • Ergonomic home office desk & chair grant
                    • Paid annual leave and wellness days
                """.trimIndent(),
                postedDate = "3 days ago",
                closingDate = "2026-11-10",
                applyUrl = "https://example.com/apply/serendib-qa-remote",
                sourceName = "Serendib Portal",
                companyWebsite = "https://example.com/serendibsys",
                isFeatured = true,
                isRemote = true,
                isPublished = true,
                isDemo = true,
                createdAt = now - (4 * day)
            ),
            JobEntity(
                id = "lk-job-005",
                title = "UI/UX Product Designer (Figma / Design Systems)",
                companyName = "Colombo Creative Studio (Demo)",
                companyDescription = "A boutique user-experience and digital product design studio crafting world-class digital experiences for consumer brands.",
                location = "Colombo 05",
                category = "IT & Software",
                employmentType = "Full-time",
                experienceLevel = "Mid-Senior Level",
                salaryMin = 200000.0,
                salaryMax = 320000.0,
                salaryCurrency = "LKR",
                description = "We are seeking a talented UI/UX Product Designer with a sharp eye for typography, micro-interactions, and scalable design systems to craft delightful mobile and web experiences.",
                responsibilities = """
                    • Create intuitive user journeys, wireframes, and interactive Figma prototypes
                    • Maintain and extend our comprehensive multi-platform design token system
                    • Conduct usability testing sessions with real Sri Lankan and regional users
                    • Bridge design specs seamlessly with frontend mobile and web engineers
                """.trimIndent(),
                requirements = """
                    • Strong portfolio displaying end-to-end mobile app or SaaS product work
                    • Mastery of Figma (components, auto-layout, variables, prototyping)
                    • Deep understanding of Material 3 and iOS Human Interface Guidelines
                    • Strong empathy for end users and accessibility standards
                """.trimIndent(),
                benefits = """
                    • Creative studio workspace with specialty Ceylon coffee bar
                    • Latest Apple iPad Pro and Apple Pencil for sketching
                    • Hybrid remote work (2 days studio, 3 days home)
                    • Annual design conference travel grant
                """.trimIndent(),
                postedDate = "3 days ago",
                closingDate = "2026-10-28",
                applyUrl = "https://example.com/apply/colombo-uiux",
                sourceName = "Design Colombo",
                companyWebsite = "https://example.com/colombocreative",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (5 * day)
            ),
            JobEntity(
                id = "lk-job-006",
                title = "Digital Marketing & Performance Manager",
                companyName = "Lanka Growth Media (Demo)",
                companyDescription = "Lanka Growth Media empowers e-commerce and retail brands with high-converting paid social, search engine marketing, and retention loops.",
                location = "Galle",
                category = "Marketing",
                employmentType = "Full-time",
                experienceLevel = "Mid-Senior Level",
                salaryMin = 180000.0,
                salaryMax = 280000.0,
                salaryCurrency = "LKR",
                description = "Drive client acquisition and brand visibility across Google Ads, Meta Ads, and TikTok. You will optimize conversion funnels and lead our performance marketing desk located in vibrant Galle Fort.",
                responsibilities = """
                    • Plan and manage large-scale paid ad campaigns across Meta Ads Manager and Google Ads
                    • Analyze ROAS, CAC, and conversion rate analytics using Google Analytics 4
                    • Collaborate with content creators and graphic designers on high-performing ad creatives
                    • Prepare bi-weekly performance briefs for enterprise clients
                """.trimIndent(),
                requirements = """
                    • 3+ years managing performance ad spend with verifiable ROAS track record
                    • Certified in Google Search & Display Ads and Meta Certified Media Buyer
                    • Excellent analytical mindset with Google Sheets / Looker Studio proficiency
                    • Fluent communication skills in English and Sinhala
                """.trimIndent(),
                benefits = """
                    • Coastal office setting right inside historic Galle Fort
                    • Performance-based bonus incentives tied directly to campaign ROI
                    • Generous leave policy and team retreat days
                    • Relocation assistance for candidates moving to Southern Province
                """.trimIndent(),
                postedDate = "4 days ago",
                closingDate = "2026-11-15",
                applyUrl = "https://example.com/apply/lanka-growth-marketing",
                sourceName = "Lanka Media Network",
                companyWebsite = "https://example.com/lankagrowth",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (6 * day)
            ),
            JobEntity(
                id = "lk-job-007",
                title = "Civil Site Engineer (Commercial Construction)",
                companyName = "Ruhuna Infrastructure & Engineering (Demo)",
                companyDescription = "Ruhuna Infrastructure is an ISO 9001 certified civil engineering contracting firm building highways, luxury resorts, and high-rise developments across the island.",
                location = "Kandy",
                category = "Engineering",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 160000.0,
                salaryMax = 240000.0,
                salaryCurrency = "LKR",
                description = "We have an immediate opening for a dedicated Civil Site Engineer to oversee structural concrete works, MEP coordination, and contractor supervision for a premier resort development in Kandy.",
                responsibilities = """
                    • Supervise daily construction operations, concrete pours, and structural rebar placements
                    • Review architectural blueprints and ensure adherence to CIDA specifications
                    • Monitor subcontractor progress, safety compliance, and material inventory on site
                    • Prepare daily progress reports and coordinate structural testing laboratory reports
                """.trimIndent(),
                requirements = """
                    • BSc in Civil Engineering from a recognized university or NDT/HNDE
                    • Minimum 3 years on-site structural building construction experience
                    • Strong understanding of bar bending schedules and AutoCAD drawings
                    • Ability to work on-site in Central Province
                """.trimIndent(),
                benefits = """
                    • On-site executive accommodation and meal allowance provided in Kandy
                    • Site transport and official motorcycle allowance
                    • Site hazard insurance cover
                    • Annual project completion bonus
                """.trimIndent(),
                postedDate = "5 days ago",
                closingDate = "2026-10-20",
                applyUrl = "https://example.com/apply/ruhuna-civil-kandy",
                sourceName = "Engineering Lanka",
                companyWebsite = "https://example.com/ruhunaeng",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (7 * day)
            ),
            JobEntity(
                id = "lk-job-008",
                title = "Electrical & Automation Systems Engineer",
                companyName = "Wayamba Industrial Dynamics (Demo)",
                companyDescription = "Wayamba Industrial Dynamics designs and maintains electrical sub-stations, industrial switchgear, and PLC automation setups for manufacturing facilities across Kurunegala.",
                location = "Kurunegala",
                category = "Engineering",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 175000.0,
                salaryMax = 260000.0,
                salaryCurrency = "LKR",
                description = "Seeking a qualified Electrical Engineer to program industrial PLCs, supervise plant electrical maintenance, and implement energy efficiency upgrades.",
                responsibilities = """
                    • Program and troubleshoot Siemens and Allen Bradley PLC and SCADA architectures
                    • Manage high-voltage transformer installations, power factor correction, and diesel generator sync
                    • Implement preventive maintenance schedules to eliminate factory downtime
                    • Train factory technicians on electrical safety standards and lockout/tagout procedures
                """.trimIndent(),
                requirements = """
                    • BSc in Electrical / Mechatronics Engineering or NDT Electrical
                    • 3+ years experience in manufacturing or process plant automation
                    • Solid proficiency in PLC ladder logic, VFD tuning, and electrical wiring schematics
                    • Good problem-solving mindset and willingness to attend emergency maintenance calls
                """.trimIndent(),
                benefits = """
                    • Fuel quota and vehicle maintenance reimbursement
                    • Industrial hazard medical cover
                    • Subsidized factory meals
                    • Continuous technical training programs
                """.trimIndent(),
                postedDate = "5 days ago",
                closingDate = "2026-11-01",
                applyUrl = "https://example.com/apply/wayamba-electrical",
                sourceName = "Wayamba Engineering Portal",
                companyWebsite = "https://example.com/wayambadynamics",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (8 * day)
            ),
            JobEntity(
                id = "lk-job-009",
                title = "Enterprise B2B Sales Executive",
                companyName = "Lanka Connect Telecom (Demo)",
                companyDescription = "Lanka Connect Telecom supplies enterprise leased lines, SD-WAN, and managed cybersecurity solutions to major commercial banks and export houses in Sri Lanka.",
                location = "Colombo 01",
                category = "Sales",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 150000.0,
                salaryMax = 300000.0,
                salaryCurrency = "LKR",
                description = "Drive strategic corporate account sales for enterprise cloud connectivity and managed security services with lucrative uncapped commission tiers.",
                responsibilities = """
                    • Prospect and close corporate enterprise contracts with C-level executives
                    • Conduct consultative product presentations and tailor technical proposals with pre-sales teams
                    • Maintain sales pipeline accuracy within Salesforce CRM
                    • Achieve and exceed quarterly revenue targets
                """.trimIndent(),
                requirements = """
                    • 2+ years of enterprise B2B sales experience in IT, SaaS, or telecommunications
                    • Articulate English and Sinhala presentation and negotiation skills
                    • Proven record of hitting revenue quotas
                    • Valid driving license and willingness to visit corporate clients in Colombo
                """.trimIndent(),
                benefits = """
                    • Generous uncapped monthly sales commission structure
                    • Company fuel allowance and vehicle maintenance
                    • Corporate smartphone with unlimited data package
                    • Annual overseas incentive trip for top performers
                """.trimIndent(),
                postedDate = "6 days ago",
                closingDate = "2026-11-20",
                applyUrl = "https://example.com/apply/lankaconnect-sales",
                sourceName = "Lanka Connect Careers",
                companyWebsite = "https://example.com/lankaconnect",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (9 * day)
            ),
            JobEntity(
                id = "lk-job-010",
                title = "Senior DevOps & Cloud Architect (AWS / Kubernetes)",
                companyName = "Ceylon Cloud Solutions (Demo)",
                companyDescription = "Ceylon Cloud Solutions is a premier digital technology consultancy based in Colombo, developing high-impact fintech and consumer applications across South Asia.",
                location = "Remote",
                category = "IT & Software",
                employmentType = "Full-time",
                experienceLevel = "Lead/Manager",
                salaryMin = 400000.0,
                salaryMax = 650000.0,
                salaryCurrency = "LKR",
                description = "Seeking a veteran Cloud Architect to design multi-region AWS infrastructures, automate Terraform deployments, and ensure 99.99% uptime for mission-critical payment networks.",
                responsibilities = """
                    • Design and deploy scalable Kubernetes (EKS) clusters with Terraform and Helm
                    • Implement zero-trust security postures and automated vulnerability scanning
                    • Optimize cloud spend and cloud architecture for high-throughput transactional systems
                    • Lead incident response runbooks and automated disaster recovery failovers
                """.trimIndent(),
                requirements = """
                    • 6+ years in DevOps/SRE with AWS Solutions Architect Professional certification
                    • Deep hands-on proficiency in Docker, Kubernetes, Terraform, ArgoCD, and Prometheus
                    • Strong background in Linux kernel tuning, networking, and VPC peering
                    • Excellent crisis management and communication skills
                """.trimIndent(),
                benefits = """
                    • Top-tier salary benchmarked against international standards
                    • Fully remote role with flexible working hours
                    • All AWS and Kubernetes exam certifications fully sponsored
                    • Comprehensive private medical insurance for employee and dependents
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-11-15",
                applyUrl = "https://example.com/apply/ceylon-cloud-devops",
                sourceName = "Direct Employer Portal",
                companyWebsite = "https://example.com/ceylon-cloud",
                isFeatured = true,
                isRemote = true,
                isPublished = true,
                isDemo = true,
                createdAt = now - (10 * day)
            ),
            JobEntity(
                id = "lk-job-011",
                title = "Financial Analyst (Equity Research & Valuations)",
                companyName = "Lanka FinEdge Capital (Demo)",
                companyDescription = "Lanka FinEdge is a forward-thinking fintech innovator engineering automated equity analytics and micro-investment tools for Sri Lankan and overseas investors.",
                location = "Colombo 03",
                category = "Accounting & Finance",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 180000.0,
                salaryMax = 270000.0,
                salaryCurrency = "LKR",
                description = "Perform DCF valuation models, sector outlook reports on Colombo Stock Exchange (CSE) counters, and provide actionable equity recommendations.",
                responsibilities = """
                    • Build three-statement financial models and discounted cash flow forecasts
                    • Author daily CSE market briefs and in-depth sector research reports
                    • Analyze macroeconomic indicators, Central Bank of Sri Lanka (CBSL) rate decisions, and inflation trends
                    • Assist senior portfolio managers in quarterly rebalancing strategies
                """.trimIndent(),
                requirements = """
                    • Passed Finalist of CFA (Level 2 or 3 candidate) or degree in Finance/Economics
                    • 2+ years experience in stockbroking, investment research, or financial advisory
                    • Exceptional financial modeling skills in Microsoft Excel
                    • Clear, persuasive writing and analytical abilities
                """.trimIndent(),
                benefits = """
                    • Direct exposure to top institutional fund managers
                    • Annual performance bonus linked to research accuracy
                    • CFA exam registration fees reimbursement
                    • Health and hospitalization insurance
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-10-31",
                applyUrl = "https://example.com/apply/finedge-analyst",
                sourceName = "FinEdge Careers",
                companyWebsite = "https://example.com/finedge",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (11 * day)
            ),
            JobEntity(
                id = "lk-job-012",
                title = "Customer Success Team Lead (Omnichannel Support)",
                companyName = "Kandy Global Support Center (Demo)",
                companyDescription = "Kandy Global Support Center delivers 24/7 technical customer support and ticket resolution for international SaaS providers from its modern facility in Kandy.",
                location = "Kandy",
                category = "Customer Service",
                employmentType = "Full-time",
                experienceLevel = "Lead/Manager",
                salaryMin = 140000.0,
                salaryMax = 200000.0,
                salaryCurrency = "LKR",
                description = "Lead a team of 15 customer support specialists providing chat, email, and ticketing support to global enterprise users with high CSAT ratings.",
                responsibilities = """
                    • Monitor daily queue SLA performance, first-contact resolution, and CSAT scores
                    • Coach and mentor agents through weekly 1-on-1s and quality assurance audits
                    • Escalate critical bugs to Tier 3 engineering teams with detailed diagnostic logs
                    • Optimize Zendesk and Intercom workflow automations and macro templates
                """.trimIndent(),
                requirements = """
                    • 3+ years experience in international BPO or SaaS customer support with 1+ year in leadership
                    • Exceptional oral and written English communication (C2 fluency)
                    • Familiarity with Zendesk, Jira Service Management, and Slack
                    • Flexibility to manage rotating shift rosters
                """.trimIndent(),
                benefits = """
                    • Safe transport provided for late evening and night shifts in Kandy area
                    • Night shift differential allowance
                    • Comprehensive outpatient & hospitalization insurance
                    • Friendly campus environment with game lounge
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-11-12",
                applyUrl = "https://example.com/apply/kandy-support-lead",
                sourceName = "Kandy BPO Jobs",
                companyWebsite = "https://example.com/kandysupport",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (12 * day)
            ),
            JobEntity(
                id = "lk-job-013",
                title = "Bilingual Customer Care Representative (Tamil & English)",
                companyName = "Northern Star Connect (Demo)",
                companyDescription = "Northern Star Connect operates a customer touchpoint center in Jaffna providing telecom, e-commerce, and logistics customer care.",
                location = "Jaffna",
                category = "Customer Service",
                employmentType = "Full-time",
                experienceLevel = "Entry Level",
                salaryMin = 75000.0,
                salaryMax = 110000.0,
                salaryCurrency = "LKR",
                description = "We are hiring fluent Tamil and English speaking customer service associates to assist callers with order status, bill payments, and service activations.",
                responsibilities = """
                    • Handle inbound customer inquiries courteously and efficiently
                    • Log all caller interactions into the CRM database accurately
                    • Resolve customer billing concerns and process return authorizations
                    • Meet daily first call resolution and quality compliance benchmarks
                """.trimIndent(),
                requirements = """
                    • Full fluency in Tamil and English (Sinhala proficiency is a major advantage)
                    • Passed G.C.E. Advanced Level examinations
                    • Pleasant telephone etiquette and active listening skills
                    • Basic computer and typing skills (30+ WPM)
                """.trimIndent(),
                benefits = """
                    • Structured 4-week paid training program with certified trainers
                    • Attendance incentives and monthly KPI bonuses
                    • Medical insurance cover
                    • Rapid career advancement pathway to Shift Supervisor
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-11-25",
                applyUrl = "https://example.com/apply/northern-star-jaffna",
                sourceName = "Jaffna Careers Hub",
                companyWebsite = "https://example.com/northernstar",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (13 * day)
            ),
            JobEntity(
                id = "lk-job-014",
                title = "Executive Administrative Officer & Board Secretary",
                companyName = "Lanka Export Council (Demo)",
                companyDescription = "A national commercial federation coordinating trade missions, export certifications, and bilateral trade chambers in Sri Lanka.",
                location = "Colombo 07",
                category = "Administration",
                employmentType = "Full-time",
                experienceLevel = "Mid-Senior Level",
                salaryMin = 130000.0,
                salaryMax = 190000.0,
                salaryCurrency = "LKR",
                description = "Manage administrative workflows, coordinate high-level board meetings, maintain statutory corporate archives, and oversee day-to-day secretariat functions.",
                responsibilities = """
                    • Record minutes of director board meetings and draft formal executive resolutions
                    • Coordinate executive travel itineraries, visa processing, and protocol arrangements
                    • Supervise office logistics, procurement of stationery, and service contracts
                    • Handle confidential correspondence with ministries and foreign embassies
                """.trimIndent(),
                requirements = """
                    • Bachelor's degree in Business Administration, Law, or Institute of Chartered Secretaries (ICSA) qualification
                    • 3+ years experience assisting executive directors or senior management
                    • Impeccable business English drafting and formatting skills
                    • Highly organized with meticulous attention to detail
                """.trimIndent(),
                benefits = """
                    • Prestigious diplomatic and corporate networking environment
                    • Standard government holiday calendar followed
                    • Medical insurance and provident fund contributions
                    • Annual festival allowance
                """.trimIndent(),
                postedDate = "2 weeks ago",
                closingDate = "2026-10-22",
                applyUrl = "https://example.com/apply/export-council-admin",
                sourceName = "Colombo Chamber Feed",
                companyWebsite = "https://example.com/lankaexportcouncil",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (14 * day)
            ),
            JobEntity(
                id = "lk-job-015",
                title = "Procurement & Supply Chain Coordinator",
                companyName = "Wayamba Agro Processing (Demo)",
                companyDescription = "Wayamba Agro operates coconut processing and spice value-addition plants in the North Western Province exporting to 25 countries worldwide.",
                location = "Kurunegala",
                category = "Administration",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 120000.0,
                salaryMax = 175000.0,
                salaryCurrency = "LKR",
                description = "Oversee raw material sourcing, supplier price negotiations, customs documentation, and warehousing logistics for agro-processing operations.",
                responsibilities = """
                    • Solicit supplier quotes and negotiate purchase agreements for packaging and processing inputs
                    • Coordinate container transport schedules with Colombo port clearing agents
                    • Manage warehouse inventory levels using FIFO principles and ERP stock checks
                    • Ensure suppliers adhere to fair trade and food safety certification guidelines
                """.trimIndent(),
                requirements = """
                    • Diploma or Degree in Supply Chain Management or CIPS qualification
                    • 2+ years experience in factory procurement or export logistics
                    • Proficient in Microsoft Excel and ERP stock management software
                    • Strong negotiation and supplier relationship management skills
                """.trimIndent(),
                benefits = """
                    • Company transport provided from Kurunegala town center
                    • Subsidized lunch and staff welfare benefits
                    • Year-end harvest performance bonus
                    • Medical insurance cover
                """.trimIndent(),
                postedDate = "2 weeks ago",
                closingDate = "2026-11-04",
                applyUrl = "https://example.com/apply/wayamba-procurement",
                sourceName = "Agro Jobs Lanka",
                companyWebsite = "https://example.com/wayambaagro",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (15 * day)
            ),
            JobEntity(
                id = "lk-job-016",
                title = "Secondary Mathematics & Statistics Teacher",
                companyName = "Kandy International Academy (Demo)",
                companyDescription = "A premier private international school located in Kandy preparing students for Edexcel and Cambridge IGCSE and A-Level examinations.",
                location = "Kandy",
                category = "Education",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 110000.0,
                salaryMax = 160000.0,
                salaryCurrency = "LKR",
                description = "Inspire young minds and deliver engaging mathematics and statistics lessons for Cambridge IGCSE and Advanced Level cohorts in Kandy.",
                responsibilities = """
                    • Deliver syllabus-aligned lesson plans for Pure Mathematics and Mechanics/Statistics
                    • Prepare formative assessments, mock exam papers, and comprehensive mark schemes
                    • Provide individualized remedial tutoring to support struggling learners
                    • Conduct parent-teacher conferences and provide constructive progress feedback
                """.trimIndent(),
                requirements = """
                    • Degree in Mathematics, Statistics, Engineering, or Education (B.Ed)
                    • 2+ years teaching experience in Cambridge or Edexcel IGCSE/A-Level curricula
                    • Fluent spoken and written English instruction
                    • Passion for mentoring students and extracurricular activity coordination
                """.trimIndent(),
                benefits = """
                    • Full tuition fee waiver for up to two children
                    • Generous school term vacations (approx. 10 weeks annually)
                    • Medical insurance coverage
                    • Professional Cambridge teacher training workshops
                """.trimIndent(),
                postedDate = "2 weeks ago",
                closingDate = "2026-11-18",
                applyUrl = "https://example.com/apply/kandy-maths-teacher",
                sourceName = "EduLanka Vacancies",
                companyWebsite = "https://example.com/kandyacademy",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (16 * day)
            ),
            JobEntity(
                id = "lk-job-017",
                title = "English Language & Academic Writing Lecturer",
                companyName = "Jaffna Higher Education Institute (Demo)",
                companyDescription = "An accredited tertiary institute in Jaffna offering Pearson BTEC and UK university diploma pathway programs.",
                location = "Jaffna",
                category = "Education",
                employmentType = "Part-time",
                experienceLevel = "Mid Level",
                salaryMin = 90000.0,
                salaryMax = 140000.0,
                salaryCurrency = "LKR",
                description = "Deliver undergraduate academic English modules, IELTS preparation, and research writing skills for higher diploma students.",
                responsibilities = """
                    • Teach 12 lecture hours per week covering Academic Writing and Critical Reading
                    • Evaluate essays, research proposals, and academic citations
                    • Prepare students for IELTS Band 7.0+ requirements
                """.trimIndent(),
                requirements = """
                    • BA or MA in English, Linguistics, or ELT with CELTA/DELTA credentials
                    • Experience lecturing in higher education or international exam preparation
                    • Excellent pedagogical skills and student rapport
                """.trimIndent(),
                benefits = """
                    • Flexible schedule allowing consulting or research
                    • Well-equipped modern library and digital lecture rooms
                    • Access to international educational research journals
                """.trimIndent(),
                postedDate = "2 weeks ago",
                closingDate = "2026-11-08",
                applyUrl = "https://example.com/apply/jaffna-english-lecturer",
                sourceName = "Jaffna Careers Hub",
                companyWebsite = "https://example.com/jaffnaedu",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (17 * day)
            ),
            JobEntity(
                id = "lk-job-018",
                title = "Senior Nursing Sister / Nursing In-Charge",
                companyName = "Gampaha MedCare Hospital (Demo)",
                companyDescription = "A modern 150-bed private multispecialty hospital in Gampaha providing emergency care, cardiology, maternity, and surgical facilities.",
                location = "Gampaha",
                category = "Healthcare",
                employmentType = "Full-time",
                experienceLevel = "Senior Level",
                salaryMin = 140000.0,
                salaryMax = 210000.0,
                salaryCurrency = "LKR",
                description = "Lead the nursing roster in the Intensive Care Unit (ICU) and Surgical Ward, ensuring patient safety, medication accuracy, and compassionate clinical care.",
                responsibilities = """
                    • Oversee ward nursing allocations, shift handovers, and patient monitoring protocols
                    • Administer critical IV infusions, manage ventilator settings under consultant guidance
                    • Ensure compliance with infection prevention and clinical waste disposal regulations
                    • Mentor student nurses and junior healthcare assistants
                """.trimIndent(),
                requirements = """
                    • Registered Nurse (SLMC / Nursing Council of Sri Lanka registration is mandatory)
                    • Minimum 4 years nursing experience with at least 1 year in ICU or High Dependency Unit
                    • BLS/ACLS certification
                    • Compassionate, calm demeanor in high-pressure clinical scenarios
                """.trimIndent(),
                benefits = """
                    • Subsidized staff hostel accommodation within hospital premises
                    • Comprehensive hospital treatment discount for immediate family
                    • Shift allowance and overtime payments
                    • Night shift meals provided free of charge
                """.trimIndent(),
                postedDate = "3 weeks ago",
                closingDate = "2026-10-31",
                applyUrl = "https://example.com/apply/gampaha-nursing-lead",
                sourceName = "HealthJobs Sri Lanka",
                companyWebsite = "https://example.com/gampahamedcare",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (18 * day)
            ),
            JobEntity(
                id = "lk-job-019",
                title = "Medical Laboratory Technologist (MLT)",
                companyName = "Colombo Diagnostic Laboratories (Demo)",
                companyDescription = "An ISO 15189 accredited clinical pathology and medical diagnostic testing network operating across Sri Lanka.",
                location = "Colombo 08",
                category = "Healthcare",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 110000.0,
                salaryMax = 160000.0,
                salaryCurrency = "LKR",
                description = "Perform automated hematology, biochemistry, and microbiology assays with precision calibration and strict internal quality controls.",
                responsibilities = """
                    • Operate automated clinical chemistry and hematology analyzers (Roche / Beckman Coulter)
                    • Conduct specimen preparation, blood grouping, and microscopic urine/stool analyses
                    • Verify unusual laboratory results and perform repeat testing before report release
                    • Maintain daily quality control logs, reagent inventories, and equipment calibration
                """.trimIndent(),
                requirements = """
                    • Diploma in Medical Laboratory Technology (Ministry of Health Sri Lanka) or BSc MLT
                    • Active registration with the Sri Lanka Medical Council (SLMC)
                    • 1+ year practical laboratory experience in a recognized private or government institution
                    • Strong knowledge of laboratory safety and biosafety protocols
                """.trimIndent(),
                benefits = """
                    • Shift duty allowance and specimen collection incentives
                    • Regular health screening and hepatitis immunization cover
                    • Staff uniform provided
                    • Opportunities for specialization in molecular diagnostics
                """.trimIndent(),
                postedDate = "3 weeks ago",
                closingDate = "2026-11-20",
                applyUrl = "https://example.com/apply/colombo-mlt",
                sourceName = "HealthJobs Sri Lanka",
                companyWebsite = "https://example.com/colombodiagnostics",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (19 * day)
            ),
            JobEntity(
                id = "lk-job-020",
                title = "Software Engineering Intern (Android & Backend)",
                companyName = "Ceylon Cloud Solutions (Demo)",
                companyDescription = "Ceylon Cloud Solutions is a premier digital technology consultancy based in Colombo, developing high-impact fintech and consumer applications across South Asia.",
                location = "Colombo 03",
                category = "Internships",
                employmentType = "Internship",
                experienceLevel = "Internship",
                salaryMin = 65000.0,
                salaryMax = 85000.0,
                salaryCurrency = "LKR",
                description = "A 6-month intensive engineering internship for undergraduate students to gain real production experience building Kotlin Android apps and Spring/Node microservices under senior mentorship.",
                responsibilities = """
                    • Implement Jetpack Compose UI components following Figma design files
                    • Write unit tests for business logic repositories and data mappers
                    • Participate in daily agile standups, sprint plannings, and pair programming sessions
                    • Present an end-of-internship capstone project to tech leadership
                """.trimIndent(),
                requirements = """
                    • Undergraduate in 3rd or 4th year studying Computer Science, IT, or Software Engineering
                    • Foundational knowledge of OOP, Kotlin/Java, Git, and data structures
                    • Passion for clean code, problem-solving, and continuous learning
                    • Available for a full-time 6-month internship commitment
                """.trimIndent(),
                benefits = """
                    • Monthly educational stipend of LKR 65,000 - 85,000
                    • Fast-track permanent Associate Software Engineer job offer upon graduation
                    • 1-on-1 mentorship from a Principal Engineer
                    • Team lunch Fridays and access to company learning library
                """.trimIndent(),
                postedDate = "Today",
                closingDate = "2026-10-15",
                applyUrl = "https://example.com/apply/ceylon-cloud-intern",
                sourceName = "Direct Employer Portal",
                companyWebsite = "https://example.com/ceylon-cloud",
                isFeatured = true,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (0 * day)
            ),
            JobEntity(
                id = "lk-job-021",
                title = "Digital Marketing & Social Media Intern",
                companyName = "Negombo Digital Media (Demo)",
                companyDescription = "Negombo Digital Media creates content and manages influencer campaigns for hospitality and travel brands along the western coast.",
                location = "Negombo",
                category = "Internships",
                employmentType = "Internship",
                experienceLevel = "Internship",
                salaryMin = 50000.0,
                salaryMax = 65000.0,
                salaryCurrency = "LKR",
                description = "Learn hands-on social media content creation, Reels/TikTok short-form video production, and community engagement for lifestyle brands.",
                responsibilities = """
                    • Assist in filming and editing engaging short-form video clips on smartphone
                    • Write captivating captions and schedule weekly posts using Buffer
                    • Monitor comment sections, reply to direct messages, and engage with community followers
                    • Compile weekly social media reach and impressions analytics reports
                """.trimIndent(),
                requirements = """
                    • Active social media user with an eye for current visual trends and aesthetics
                    • Basic editing skills using CapCut, Canva, or Premiere Pro
                    • Good English and Sinhala written communication
                    • Passionate, proactive, and eager to learn digital storytelling
                """.trimIndent(),
                benefits = """
                    • Monthly training stipend
                    • Potential permanent Junior Executive position after 6 months
                    • Free beachside coffee and snacks at the creative office
                    • Hands-on shoots at luxury coastal beach resorts
                """.trimIndent(),
                postedDate = "3 days ago",
                closingDate = "2026-11-01",
                applyUrl = "https://example.com/apply/negombo-marketing-intern",
                sourceName = "Western Media Jobs",
                companyWebsite = "https://example.com/negombomedia",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (3 * day)
            ),
            JobEntity(
                id = "lk-job-022",
                title = "Finance & Audit Intern",
                companyName = "Apex Lanka Holdings (Demo)",
                companyDescription = "Apex Lanka Holdings is a diversified conglomerate with active business operations spanning renewable energy, hospitality, logistics, and real estate development.",
                location = "Colombo 07",
                category = "Internships",
                employmentType = "Internship",
                experienceLevel = "Internship",
                salaryMin = 55000.0,
                salaryMax = 70000.0,
                salaryCurrency = "LKR",
                description = "Gain valuable practical audit experience, bank reconciliation skills, and ERP exposure with a leading diversified corporate group.",
                responsibilities = """
                    • Assist senior internal auditors with voucher verifications and bank reconciliations
                    • Verify physical inventory stock takes across retail and hotel subsidiaries
                    • Cross-check supplier invoices against purchase orders and goods receipt notes
                    • Prepare audit working paper schedules in Microsoft Excel
                """.trimIndent(),
                requirements = """
                    • Currently pursuing CA Sri Lanka (Executive/Business level) or ACCA/CIMA/Degree
                    • Good computer literacy in MS Excel (VLOOKUP, Pivot Tables)
                    • Honest, punctual, and detail-oriented mindset
                    • Available for a 6 to 12 month internship
                """.trimIndent(),
                benefits = """
                    • Monthly financial stipend
                    • Approved training partner for CA Sri Lanka and ACCA articles
                    • Subsidized executive cafeteria lunch
                    • Opportunity to be absorbed as Audit Trainee
                """.trimIndent(),
                postedDate = "4 days ago",
                closingDate = "2026-10-29",
                applyUrl = "https://example.com/apply/apex-audit-intern",
                sourceName = "Apex Corporate Careers",
                companyWebsite = "https://example.com/apexlanka",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (4 * day)
            ),
            JobEntity(
                id = "lk-job-023",
                title = "Part-Time Evening Customer Support Specialist",
                companyName = "GlobalConnect BPO (Demo)",
                companyDescription = "GlobalConnect offers outsourced customer care and back-office processing services for US and UK enterprise clients.",
                location = "Remote",
                category = "Part Time",
                employmentType = "Part-time",
                experienceLevel = "Entry Level",
                salaryMin = 80000.0,
                salaryMax = 110000.0,
                salaryCurrency = "LKR",
                description = "Work 4 hours per evening from the comfort of your home assisting international customers with email and live chat queries.",
                responsibilities = """
                    • Handle incoming live chat queries between 6:00 PM and 10:00 PM Sri Lanka time
                    • Assist customers with password resets, product inquiries, and order tracking
                    • Maintain an average customer satisfaction score of 90%+
                """.trimIndent(),
                requirements = """
                    • Fluent written English proficiency with good typing speed (40+ WPM)
                    • Stable home internet connection with power backup (UPS/Inverter)
                    • Personal laptop with minimum 8GB RAM
                    • Ability to work 5 evenings per week (including one weekend day)
                """.trimIndent(),
                benefits = """
                    • Ideal for university students or individuals looking for supplementary income
                    • Monthly home internet allowance
                    • Hourly rate with weekend bonuses
                    • Fully remote flexibility
                """.trimIndent(),
                postedDate = "5 days ago",
                closingDate = "2026-11-20",
                applyUrl = "https://example.com/apply/globalconnect-evening",
                sourceName = "Remote Lanka Jobs",
                companyWebsite = "https://example.com/globalconnect",
                isFeatured = false,
                isRemote = true,
                isPublished = true,
                isDemo = true,
                createdAt = now - (5 * day)
            ),
            JobEntity(
                id = "lk-job-024",
                title = "Weekend Retail Sales & Showroom Assistant",
                companyName = "Negombo Lifestyle Boutique (Demo)",
                companyDescription = "An upscale resort wear, Ceylon sapphire jewelry, and artisanal lifestyle showroom welcoming tourists and locals in Negombo.",
                location = "Negombo",
                category = "Part Time",
                employmentType = "Part-time",
                experienceLevel = "Entry Level",
                salaryMin = 45000.0,
                salaryMax = 65000.0,
                salaryCurrency = "LKR",
                description = "Welcome visitors on Saturdays and Sundays, assist with merchandise inquiries, display presentation, and POS billing operations.",
                responsibilities = """
                    • Greet showroom customers warmly and assist with product recommendations
                    • Keep merchandise displays clean, steam clothing, and ensure neat inventory
                    • Process credit card transactions on POS machine and issue receipts
                    • Tally daily weekend sales at closing
                """.trimIndent(),
                requirements = """
                    • Pleasant outgoing personality with positive customer attitude
                    • Conversational English fluency (foreign tourist interactions)
                    • Honest, dependable, and available to work Saturdays and Sundays (9:30 AM to 6:30 PM)
                """.trimIndent(),
                benefits = """
                    • Weekend sales commission on boutique jewelry items
                    • Free weekend lunch provided
                    • Employee purchase discount (30% off showroom items)
                """.trimIndent(),
                postedDate = "6 days ago",
                closingDate = "2026-11-10",
                applyUrl = "https://example.com/apply/negombo-weekend-retail",
                sourceName = "Western Media Jobs",
                companyWebsite = "https://example.com/negomboboutique",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (6 * day)
            ),
            JobEntity(
                id = "lk-job-025",
                title = "Senior Flutter & Cross-Platform Mobile Engineer",
                companyName = "Island Tech Innovations (Demo)",
                companyDescription = "Island Tech Innovations engineers modern logistics, parcel tracking, and merchant tools utilized across Sri Lanka and Southeast Asia.",
                location = "Remote",
                category = "Remote",
                employmentType = "Full-time",
                experienceLevel = "Mid-Senior Level",
                salaryMin = 300000.0,
                salaryMax = 420000.0,
                salaryCurrency = "LKR",
                description = "Build high-speed, battery-efficient mobile applications using Flutter & Dart. Work with map APIs, offline caching, and real-time Bluetooth thermal printing integrations.",
                responsibilities = """
                    • Develop performant Flutter apps targeting both Android and iOS from a unified codebase
                    • Implement state management with Riverpod or BLoC architecture
                    • Integrate hardware peripherals via platform channels (Barcode scanners, Bluetooth printers)
                    • Continuously profile app frame rates and network bandwidth consumption
                """.trimIndent(),
                requirements = """
                    • 3+ years experience with Flutter in commercial applications
                    • Deep understanding of Dart streams, async programming, and state management
                    • Experience publishing apps to Google Play Store and Apple App Store
                    • Strong communication in remote team environment
                """.trimIndent(),
                benefits = """
                    • 100% remote job permanently
                    • Dollar-pegged remuneration with quarterly review
                    • Latest test mobile devices provided
                    • 25 days paid annual leave
                """.trimIndent(),
                postedDate = "2 days ago",
                closingDate = "2026-11-25",
                applyUrl = "https://example.com/apply/islandtech-flutter-remote",
                sourceName = "Remote Lanka Jobs",
                companyWebsite = "https://example.com/islandtech",
                isFeatured = true,
                isRemote = true,
                isPublished = true,
                isDemo = true,
                createdAt = now - (2 * day)
            ),
            JobEntity(
                id = "lk-job-026",
                title = "Mechanical Quality & Safety Inspector",
                companyName = "Kegalle Heavy Engineering (Demo)",
                companyDescription = "Kegalle Heavy Engineering fabricates industrial tea driers, rubber processing machinery, and steel structural components in the Sabaragamuwa Province.",
                location = "Kegalle",
                category = "Engineering",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 145000.0,
                salaryMax = 210000.0,
                salaryCurrency = "LKR",
                description = "Inspect welding joints, structural steel fabrications, dimensional tolerances, and plant safety systems in our Kegalle fabrication facility.",
                responsibilities = """
                    • Perform non-destructive testing (NDT), ultrasonic and dye penetrant testing on welded joints
                    • Verify mechanical tolerances against engineering drawings using precision calipers and micrometers
                    • Ensure compliance with factory safety protocols, fire regulations, and OHSAS standards
                    • Author non-conformance reports (NCR) and recommend corrective engineering actions
                """.trimIndent(),
                requirements = """
                    • NDT / NDES / HNDE in Mechanical Engineering or CSWIP 3.1 certification
                    • 3+ years inspection experience in steel fabrication or heavy manufacturing
                    • Sound understanding of ISO quality management frameworks
                    • Strong observational and reporting skills
                """.trimIndent(),
                benefits = """
                    • Subsidized factory housing in Kegalle
                    • Performance and safety bonus
                    • Safety gear and equipment provided
                    • Medical insurance for employee and spouse
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-10-31",
                applyUrl = "https://example.com/apply/kegalle-mechanical-inspector",
                sourceName = "Engineering Lanka",
                companyWebsite = "https://example.com/kegalleheavy",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (7 * day)
            ),
            JobEntity(
                id = "lk-job-027",
                title = "Graphic Designer & Brand Identity Specialist",
                companyName = "Serendib Creative Labs (Demo)",
                companyDescription = "Serendib Creative Labs designs premium packaging, brand identities, and retail marketing materials for export tea and wellness brands.",
                location = "Galle",
                category = "Marketing",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 150000.0,
                salaryMax = 230000.0,
                salaryCurrency = "LKR",
                description = "Create luxury packaging artwork, vector illustrations, brand guidelines, and print-ready prepress files for Sri Lankan premium export goods.",
                responsibilities = """
                    • Design brand identity systems, typography, color palettes, and packaging die-lines
                    • Prepare color-accurate CMYK print production files for embossing, foil stamping, and UV spot coatings
                    • Collaborate with copywriters and marketing strategists on creative concept decks
                    • Ensure visual consistency across print, digital, and social channels
                """.trimIndent(),
                requirements = """
                    • Portfolio demonstrating luxury packaging and brand identity projects
                    • Advanced mastery of Adobe Illustrator, Photoshop, and InDesign
                    • Strong understanding of prepress printing processes and paper substrates
                    • Good aesthetic sensibility and typographic refinement
                """.trimIndent(),
                benefits = """
                    • Inspiring open-plan studio in Galle
                    • High-end 4K color-calibrated monitor and drawing tablet
                    • Annual creative retreat
                    • Flexible working hours
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-11-15",
                applyUrl = "https://example.com/apply/serendib-creative-designer",
                sourceName = "Design Colombo",
                companyWebsite = "https://example.com/serendibcreative",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (8 * day)
            ),
            JobEntity(
                id = "lk-job-028",
                title = "Business Intelligence & Data Analyst",
                companyName = "Lanka FinEdge Capital (Demo)",
                companyDescription = "Lanka FinEdge is a forward-thinking fintech innovator engineering automated equity analytics and micro-investment tools for Sri Lankan and overseas investors.",
                location = "Colombo 02",
                category = "IT & Software",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 220000.0,
                salaryMax = 320000.0,
                salaryCurrency = "LKR",
                description = "Transform complex transactional and customer data into interactive Power BI dashboards, automated SQL queries, and predictive user retention insights.",
                responsibilities = """
                    • Write advanced PostgreSQL queries and dbt data transformations
                    • Design executive dashboards in Power BI and Metabase with automated refreshes
                    • Partner with product managers to define tracking events and user lifecycle funnels
                    • Identify transaction anomalies and customer churn risk indicators
                """.trimIndent(),
                requirements = """
                    • 2+ years working with SQL, data warehouses (BigQuery/Snowflake), and Power BI or Tableau
                    • Strong statistical knowledge and analytical curiosity
                    • Experience with Python (pandas, numpy) for exploratory data analysis
                    • Degree in Statistics, Data Science, Computer Science, or Mathematics
                """.trimIndent(),
                benefits = """
                    • Market-competitive remuneration with quarterly performance bonuses
                    • Premium health insurance cover
                    • Hybrid remote flexibility
                    • Microsoft Power BI certified training sponsorship
                """.trimIndent(),
                postedDate = "4 days ago",
                closingDate = "2026-11-08",
                applyUrl = "https://example.com/apply/finedge-bi-analyst",
                sourceName = "FinEdge Careers",
                companyWebsite = "https://example.com/finedge",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (4 * day)
            ),
            JobEntity(
                id = "lk-job-029",
                title = "Hospitality Front Office & Guest Relations Executive",
                companyName = "Southern Coral Beach Resort (Demo)",
                companyDescription = "Southern Coral is a 5-star boutique beachfront resort in Galle welcoming travelers from across the globe.",
                location = "Galle",
                category = "Customer Service",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 100000.0,
                salaryMax = 150000.0,
                salaryCurrency = "LKR",
                description = "Provide memorable check-in experiences, personalized excursion itineraries, and VIP guest care at our luxury coastal resort.",
                responsibilities = """
                    • Welcome international guests, process check-ins, and present room amenities
                    • Coordinate guest transport, whale watching tours, and historic Galle Fort excursions
                    • Address guest feedback proactively to achieve five-star TripAdvisor ratings
                    • Manage Opera / Fidelio property management system bookings
                """.trimIndent(),
                requirements = """
                    • Diploma in Hospitality Management from SLITHM or equivalent
                    • 2+ years front office experience in a 4-star or 5-star hotel
                    • Outstanding English fluency (knowledge of French, German, or Russian is an added bonus)
                    • Polished grooming and courteous hospitality disposition
                """.trimIndent(),
                benefits = """
                    • High monthly service charge distribution (often exceeds base salary during peak tourist season)
                    • Duty meals and executive staff accommodation in Galle
                    • Comprehensive medical insurance
                    • Discounted stays across affiliated resort properties
                """.trimIndent(),
                postedDate = "5 days ago",
                closingDate = "2026-11-30",
                applyUrl = "https://example.com/apply/southern-coral-frontoffice",
                sourceName = "Hospitality Lanka",
                companyWebsite = "https://example.com/southerncoral",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (5 * day)
            ),
            JobEntity(
                id = "lk-job-030",
                title = "Assistant Brand Manager (Consumer Packaged Goods)",
                companyName = "Apex Lanka Holdings (Demo)",
                companyDescription = "Apex Lanka Holdings is a diversified conglomerate with active business operations spanning renewable energy, hospitality, logistics, and real estate development.",
                location = "Colombo 07",
                category = "Marketing",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 170000.0,
                salaryMax = 250000.0,
                salaryCurrency = "LKR",
                description = "Lead promotional campaigns, packaging redesigns, and supermarket activation strategies for our flagship FMCG beverage brands.",
                responsibilities = """
                    • Execute comprehensive 360-degree marketing launch campaigns for new consumer products
                    • Manage creative agency deliverables, TV commercial scripts, and digital ad shoots
                    • Monitor retail market share, Nielsen scan track data, and trade promotion effectiveness
                    • Plan modern trade activation displays in Arpico, Keells, and Cargills retail outlets
                """.trimIndent(),
                requirements = """
                    • CIM or SLIM qualification or Degree in Marketing
                    • 2.5+ years experience in brand marketing within FMCG / CPG sector
                    • Strong presentation, budgeting, and project management skills
                    • Energetic, creative, and proactive attitude
                """.trimIndent(),
                benefits = """
                    • Travel and fuel allowance
                    • Executive health insurance
                    • Performance-linked annual bonus
                    • Staff product discount quotas
                """.trimIndent(),
                postedDate = "6 days ago",
                closingDate = "2026-11-10",
                applyUrl = "https://example.com/apply/apex-brand-manager",
                sourceName = "Apex Corporate Careers",
                companyWebsite = "https://example.com/apexlanka",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (6 * day)
            ),
            JobEntity(
                id = "lk-job-031",
                title = "Junior Banking Operations Associate",
                companyName = "Lanka Commercial Trust (Demo)",
                companyDescription = "A premier licensed commercial financial institution providing retail, SME, and trade financing across all 9 provinces of Sri Lanka.",
                location = "Gampaha",
                category = "Accounting & Finance",
                employmentType = "Full-time",
                experienceLevel = "Entry Level",
                salaryMin = 85000.0,
                salaryMax = 120000.0,
                salaryCurrency = "LKR",
                description = "Execute core retail banking teller transactions, savings account openings, fixed deposit bookings, and customer service operations at our Gampaha branch.",
                responsibilities = """
                    • Process cash deposits, withdrawals, and clearing cheques with high accuracy
                    • Open new individual and SME current/savings accounts complying with CBSL KYC/AML regulations
                    • Cross-sell bank debit cards, internet banking registrations, and loan products
                    • Tally daily cash drawers and balance treasury cash book
                """.trimIndent(),
                requirements = """
                    • Passed G.C.E. Advanced Level with credits for Mathematics or Commerce stream
                    • Part qualification in IBSL (Institute of Bankers of Sri Lanka) is preferred
                    • Good interpersonal skills and professional ethical conduct
                    • Computer literacy in core banking applications
                """.trimIndent(),
                benefits = """
                    • Concessionary staff loan schemes after confirmation
                    • Attractive banking medical scheme for self and family
                    • Annual banking bonus and holiday bungalow privileges
                    • Clear career progression to Assistant Branch Manager
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-11-28",
                applyUrl = "https://example.com/apply/lanka-trust-bank-gampaha",
                sourceName = "Banking Lanka",
                companyWebsite = "https://example.com/lankatrust",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (9 * day)
            ),
            JobEntity(
                id = "lk-job-032",
                title = "Logistics Operations & Dispatch Supervisor",
                companyName = "Colombo Express Logistics (Demo)",
                companyDescription = "Colombo Express Logistics manages island-wide courier distribution, temperature-controlled fleet deliveries, and e-commerce last-mile fulfillments.",
                location = "Kurunegala",
                category = "Administration",
                employmentType = "Full-time",
                experienceLevel = "Mid Level",
                salaryMin = 110000.0,
                salaryMax = 160000.0,
                salaryCurrency = "LKR",
                description = "Supervise delivery route schedules, vehicle dispatch rosters, and parcel scan reconcilements at our central Kurunegala regional distribution hub.",
                responsibilities = """
                    • Coordinate daily delivery routes for 20+ delivery vans and courier riders
                    • Monitor GPS fleet tracking to ensure timely dispatch and minimize transit delays
                    • Resolve delivery discrepancies, customer return parcels, and cash-on-delivery (COD) settlements
                    • Maintain vehicle service logs and driver safety adherence
                """.trimIndent(),
                requirements = """
                    • 2+ years experience in logistics, courier operations, or warehouse supervision
                    • Excellent geographical knowledge of North Western and Central road networks
                    • Good command of Sinhala and conversational English
                    • Able to handle dynamic warehouse pace and leadership of riders
                """.trimIndent(),
                benefits = """
                    • Hub performance incentive based on on-time delivery rate
                    • Mobile phone allowance
                    • Personal accident insurance cover
                    • Provident fund and ETF benefits
                """.trimIndent(),
                postedDate = "1 week ago",
                closingDate = "2026-11-14",
                applyUrl = "https://example.com/apply/colombo-express-kurunegala",
                sourceName = "Lanka Logistics Hub",
                companyWebsite = "https://example.com/colomboexpress",
                isFeatured = false,
                isRemote = false,
                isPublished = true,
                isDemo = true,
                createdAt = now - (10 * day)
            )
        )
    }
}
